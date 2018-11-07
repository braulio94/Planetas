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

    List<Planet> planets;
    Context context;

    public PlanetAdapter(Context ctx) {
        this.context = ctx;
        this.planets = new ArrayList<>();
        planets.add(new Planet("Terra", "Planet com pessoas",R.drawable.earth ));
        planets.add(new Planet("Jupiter", "Planeta a 8 mil anos de luz", R.drawable.jupiter));
        planets.add(new Planet("Marte", "Planeta vermelho", R.drawable.mars));
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {
        Planet planet = planets.get(position);
        holder.image.setImageResource(planet.res);
        holder.name.setText(planet.name);
        holder.description.setText(planet.description);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class PlanetViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView image;

        public PlanetViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.planet_name);
            description = itemView.findViewById(R.id.planet_description);
            image = itemView.findViewById(R.id.planet_image);
        }
    }
}
