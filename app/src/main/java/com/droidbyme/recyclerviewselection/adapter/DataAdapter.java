package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.activity.DistrictViewActivity;
import com.droidbyme.recyclerviewselection.model.StateInformation;

import java.util.ArrayList;
import java.util.Locale;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {

    private Context context;
    private ArrayList<StateInformation> informationArrayList;

    public DataAdapter(Context context, ArrayList<StateInformation> informationArrayList) {
        this.context = context;
        this.informationArrayList = informationArrayList;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_state, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        StateInformation stateInformation = informationArrayList.get(position);
        holder.setDetails(stateInformation);
    }

    @Override
    public int getItemCount() {
        return informationArrayList.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtConfirmed, txtActive, txtRecovered, txtDeceased;
        private LinearLayout stateLinearLayoutView;

        DataHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtConfirmed = itemView.findViewById(R.id.txtConfirmed);
            txtActive = itemView.findViewById(R.id.txtActive);
            txtRecovered = itemView.findViewById(R.id.txtRecovered);
            txtDeceased = itemView.findViewById(R.id.txtDeceased);
            stateLinearLayoutView = itemView.findViewById(R.id.stateLinearLayoutView);

            stateLinearLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,txtName.getText() + " Selected", Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(context, DistrictViewActivity.class);
//                    intent.putExtra("stateName", txtName.getText());
//                    context.startActivity(intent);
                }
            });
        }

        void setDetails(StateInformation stateInformation) {
            txtName.setText(stateInformation.getStateName());
            txtConfirmed.setText(String.format(Locale.US, "Confirmed : %d", stateInformation.getConfirmed()));
            txtActive.setText(String.format(Locale.US, "Hospitalized : %d", stateInformation.getActive()));
            txtRecovered.setText(String.format(Locale.US, "Recovered : %d", stateInformation.getRecovered()));
            txtDeceased.setText(String.format(Locale.US, "Deceased : %d", stateInformation.getDeceased()));
        }
    }
}