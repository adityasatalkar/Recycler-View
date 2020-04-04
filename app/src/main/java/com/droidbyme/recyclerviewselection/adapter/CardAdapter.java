package com.droidbyme.recyclerviewselection.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.model.Planet;

import java.util.ArrayList;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.PlanetHolder> {

    private Context context;
    private ArrayList<Planet> planets;

    public CardAdapter(Context context, ArrayList<Planet> planets) {
        this.context = context;
        this.planets = planets;
    }

    @NonNull
    @Override
    public PlanetHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new PlanetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetHolder holder, int position) {
        Planet planet = planets.get(position);
        holder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class PlanetHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtConfirmed, txtActive, txtRecovered, txtDeceased;

        PlanetHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtConfirmed = itemView.findViewById(R.id.txtConfirmed);
            txtActive = itemView.findViewById(R.id.txtActive);
            txtRecovered = itemView.findViewById(R.id.txtRecovered);
            txtDeceased = itemView.findViewById(R.id.txtDeceased);
        }

        void setDetails(Planet planet) {
            txtName.setText(planet.getStateName());
            txtConfirmed.setText(String.format(Locale.US, "Confirmed : %d", planet.getConfirmed()));
            txtActive.setText(String.format(Locale.US, "Hospitalized : %d", planet.getActive()));
            txtRecovered.setText(String.format(Locale.US, "Recovered : %d", planet.getRecovered()));
            txtDeceased.setText(String.format(Locale.US, "Deceased : %d", planet.getDeceased()));
        }
    }
}