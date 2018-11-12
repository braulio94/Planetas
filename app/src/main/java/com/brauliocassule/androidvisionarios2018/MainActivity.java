package com.brauliocassule.androidvisionarios2018;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    RecyclerView planetRecyclerView;
    PlanetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        planetRecyclerView = findViewById(R.id.planet_recycler_view);
        planetRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlanetAdapter(this);
        planetRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.planets.add(new Planet("Saturno", "Planeta muito, muito distante", R.drawable.saturn));
                adapter.notifyDataSetChanged();
            }
        });
    }
}


