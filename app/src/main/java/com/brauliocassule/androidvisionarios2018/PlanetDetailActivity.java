package com.brauliocassule.androidvisionarios2018;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanetDetailActivity extends AppCompatActivity {

    ImageView planetDetailImage;
    TextView planetDetailTextName;
    TextView planetTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int res = getIntent().getIntExtra("planet_image", R.drawable.saturn);
        String name = getIntent().getStringExtra("plane_name");
        String description = getIntent().getStringExtra("planet_description");

        planetDetailImage = findViewById(R.id.planet_image);
        planetDetailImage.setImageResource(res);

        planetTextDescription = findViewById(R.id.planet_description);
        planetTextDescription.setText(description);

        planetDetailTextName = findViewById(R.id.planet_name_detail);
        planetDetailTextName.setText(name);
    }
}