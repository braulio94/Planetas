package com.brauliocassule.androidvisionarios2018;


import android.content.Intent;
import android.os.Bundle;
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
    }
}


