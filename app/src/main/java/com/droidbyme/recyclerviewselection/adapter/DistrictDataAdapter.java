package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.district.DistrictDatum;

import java.util.ArrayList;
import java.util.Locale;

public class DistrictDataAdapter  extends RecyclerView.Adapter<DistrictDataAdapter.DistrictWiseDataHolder> {

    private Context context;
    private ArrayList<DistrictDatum> districtDatumArrayList;

    public DistrictDataAdapter(Context context, ArrayList<DistrictDatum> informationArrayList) {
        this.context = context;
        this.districtDatumArrayList = informationArrayList;
    }

    @NonNull
    @Override
    public DistrictWiseDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_state, parent, false);
        return new DistrictDataAdapter.DistrictWiseDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictDataAdapter.DistrictWiseDataHolder holder, int position) {
        DistrictDatum districtWise = districtDatumArrayList.get(position);
        holder.setDetails(districtWise);
    }

    @Override
    public int getItemCount() {
        return districtDatumArrayList.size();
    }

    class DistrictWiseDataHolder extends RecyclerView.ViewHolder {

        private TextView txtDistrictName, txtConfirmed;
        private LinearLayout stateLinearLayoutView;

        DistrictWiseDataHolder(View itemView) {
            super(itemView);
            txtDistrictName = itemView.findViewById(R.id.txtDistrictName);
            txtConfirmed = itemView.findViewById(R.id.txtConfirmed);
        }

        void setDetails(DistrictDatum districtWise) {
            txtDistrictName.setText(districtWise.getDistrict());
            txtConfirmed.setText(String.format(Locale.US, "Confirmed : %d", districtWise.getConfirmed()));
        }
    }
}
