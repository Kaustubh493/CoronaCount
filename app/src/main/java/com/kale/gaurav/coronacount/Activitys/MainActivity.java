package com.kale.gaurav.coronacount.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.kale.gaurav.coronacount.Adapter.CountryAdapter;
import com.kale.gaurav.coronacount.Models.Area;
import com.kale.gaurav.coronacount.Models.Example;
import com.kale.gaurav.coronacount.R;
import com.kale.gaurav.coronacount.Utils.BloggerAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView total_infected, total_recover, total_death;
    RecyclerView rv_countries;
    List<Area> areaList = new ArrayList<>();
    CountryAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        getData();

        adapter = new CountryAdapter(this,areaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv_countries.setLayoutManager(mLayoutManager);
//        rv_countries.setAdapter(adapter);
    }

    private void init() {
        total_infected = (TextView) findViewById(R.id.total_infected);
        total_recover = (TextView) findViewById(R.id.total_recover);
        total_death = (TextView) findViewById(R.id.total_death);
        rv_countries = (RecyclerView) findViewById(R.id.rv_countries);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
    }

    private void getData() {
        Call<Example> myData = BloggerAPI.getService().getWorldData();

        myData.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                areaList.clear();
                Toast.makeText(MainActivity.this, "data getting", Toast.LENGTH_SHORT).show();
                Log.w("data", "onResponse: " + response.body());

                total_infected.setText(response.body().getTotalConfirmed().toString());
                total_recover.setText(response.body().getTotalRecovered().toString());
                total_death.setText(response.body().getTotalDeaths().toString());

                areaList = response.body().getAreas();
                adapter = new CountryAdapter(MainActivity.this,areaList);
                rv_countries.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
