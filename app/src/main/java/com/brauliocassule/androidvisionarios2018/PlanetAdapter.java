package com.brauliocassule.androidvisionarios2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {

    Context mContext;
    List<Planet> planets;

    public PlanetAdapter(Context context) {
        this.mContext = context;
        planets = new ArrayList<>();
        planets.add(new Planet(R.drawable.earth, "Terra", "Habitantes: 7.5 Biloes"));
        planets.add(new Planet(R.drawable.mercury, "Mercurio", "Habitantes: Uma formiga"));
        planets.add(new Planet(R.drawable.saturn, "Saturno", "Distancia: 8.000 ano de luz"));
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, viewGroup, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {
        Planet planet = planets.get(position);
        holder.planetImage.setImageResource(planet.getRes());
        holder.planetName.setText(planet.getName());
        holder.planetDescription.setText(planet.getDescription());
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class PlanetViewHolder extends RecyclerView.ViewHolder {

        ImageView planetImage;
        TextView planetName;
        TextView planetDescription;

        public PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            planetImage = itemView.findViewById(R.id.planet_image);
            planetName = itemView.findViewById(R.id.plate_name);
            planetDescription = itemView.findViewById(R.id.planet_description);
        }
    }
}
