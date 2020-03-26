package com.kale.gaurav.coronacount.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.kale.gaurav.coronacount.R;
import com.kale.gaurav.coronacount.Utils.SPHelper;

public class DetailsDialog extends Dialog {
    Context context;

    public DetailsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView total_infected = (TextView) findViewById(R.id.total_infected);
        TextView total_recover = (TextView) findViewById(R.id.total_recover);
        TextView total_death = (TextView) findViewById(R.id.total_death);
        TextView country = (TextView) findViewById(R.id.tv_country);

        total_infected.setText(SPHelper.getSP(getContext(), "confirm"));
        total_recover.setText(SPHelper.getSP(getContext(), "recover"));
        total_death.setText(SPHelper.getSP(getContext(), "death"));
        country.setText(SPHelper.getSP(getContext(),"country"));
    }


}
