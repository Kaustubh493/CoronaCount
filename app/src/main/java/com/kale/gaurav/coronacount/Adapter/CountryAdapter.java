package com.kale.gaurav.coronacount.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kale.gaurav.coronacount.Dialogs.DetailsDialog;
import com.kale.gaurav.coronacount.Models.Area;
import com.kale.gaurav.coronacount.R;
import com.kale.gaurav.coronacount.Utils.SPHelper;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Area> list;

    public CountryAdapter(Context context, List<Area> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new CountryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CountryAdapter.MyViewHolder){
            final Area area = list.get(position);

            final CountryAdapter.MyViewHolder holder_A = (CountryAdapter.MyViewHolder)holder;

            try{
                holder_A.tv_country_name.setText(area.getDisplayName());
                holder_A.tv_country_count.setText(area.getTotalConfirmed().toString());

                holder_A.card_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SPHelper.setSP(context,"confirm",area.getTotalConfirmed().toString());
                        SPHelper.setSP(context,"recover",area.getTotalRecovered().toString());
                        SPHelper.setSP(context,"death",area.getTotalDeaths().toString());
                        SPHelper.setSP(context,"country",area.getDisplayName().toString());

                        DetailsDialog yourDialog = new DetailsDialog(context);
                        yourDialog.show();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_country_name,tv_country_count;
        CardView card_view;
        public MyViewHolder(View itemView){
            super(itemView);
            tv_country_name = (TextView)itemView.findViewById(R.id.tv_country_name);
            tv_country_count = (TextView)itemView.findViewById(R.id.tv_country_count);
            card_view = (CardView)itemView.findViewById(R.id.card_view);
        }
    }
}


