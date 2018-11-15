package com.brauliocassule.androidvisionarios2018;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //https://github.com/braulio94/Planetas
    //Picasso
    //https://github.com/square/Picasso
    //Gson
    //https://github.com/google/gson

    RecyclerView planetRecyclerView;
    PlanetAdapter adapter;
    Dialog addItemDialog;
    ImageView newImage;
    EditText newTitle;
    EditText newDescription;
    Button saveButton;
    String path;

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
                showDialog();
            }
        });
    }

    public void showDialog(){
        addItemDialog = new Dialog(MainActivity.this);
        addItemDialog.setContentView(R.layout.add_item_dialog);

        newImage = addItemDialog.findViewById(R.id.new_image);
        newImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStoragePermissionGranted()){
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, 12);
                } else {
                    Toast.makeText(MainActivity.this, "Voce nao tem permissao", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            12);
                }
            }
        });

        newTitle = addItemDialog.findViewById(R.id.new_title);
        newDescription = addItemDialog.findViewById(R.id.new_description);

        saveButton = addItemDialog.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewItem();
            }
        });

        addItemDialog.setTitle("Adicionar novo item");
        addItemDialog.show();
    }

    public void saveNewItem(){
        if(newTitle.getText().toString().isEmpty() || newDescription.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Os campos nao podem estar nulos", Toast.LENGTH_LONG).show();
        } else {
            String title = newTitle.getText().toString();
            String description = newDescription.getText().toString();
            adapter.planets.add(
              new Planet(title, description, path)
            );
            adapter.notifyDataSetChanged();
            PlanetSharedPreferences.savePlanetList(adapter.planets, MainActivity.this, "planets");
            path = null;
            addItemDialog.dismiss();
        }
    }

    public boolean isStoragePermissionGranted(){
        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Uri uri = data.getData();
            Log.i("MainActivity", "Localizacao do Ficheiro: " + uri.getPath());
            path = getRealPathFromURI(uri);
            File file = new File(path);
            Picasso.get().load(file).into(newImage);
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().
                query(contentURI, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        String result = cursor.getString(idx);
        cursor.close();
        return result;
    }

}