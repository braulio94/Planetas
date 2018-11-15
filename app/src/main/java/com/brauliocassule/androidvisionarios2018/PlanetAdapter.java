package com.brauliocassule.androidvisionarios2018;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {

    List<Planet> planets;
    Context context;

    public PlanetAdapter(Context ctx) {
        this.context = ctx;
        this.planets = new ArrayList<>();
        List<Planet> savedPlanets = PlanetPreferences.getPlanetList("planets", context);
        if (savedPlanets != null){
            this.planets.addAll(savedPlanets);
        }
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlanetViewHolder holder, int position) {
        final Planet planet = planets.get(position);
        //Codigo de hoge
        Picasso.get().load(new File(planet.path)).into(holder.image);

        holder.name.setText(planet.name);
        holder.description.setText(planet.description);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.openActivity(planet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class PlanetViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView image;

        //Shared Preferences
        //Database Local (SQLite, Realm, Room...)


        public PlanetViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.planet_name);
            description = itemView.findViewById(R.id.planet_description);
            image = itemView.findViewById(R.id.planet_image);
        }

        public void openActivity(Planet planet){
            Intent intent = new Intent(context, PlanetDetailActivity.class);
            intent.putExtra("plane_name", planet.getName());
            intent.putExtra("planet_description", planet.getDescription());
            //intent.putExtra("planet_image", planet.getRes());
            //context.startActivity(intent);
        }
    }
}
