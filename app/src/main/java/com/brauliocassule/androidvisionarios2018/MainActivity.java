package com.brauliocassule.androidvisionarios2018;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    RecyclerView planetRecyclerView;
    PlanetAdapter adapter;
    Dialog addItemDialog;
    String path;
    ImageView newImageView;
    TextView newTitle;
    TextView newDescription;
    Button saveButton;

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

        newImageView = addItemDialog.findViewById(R.id.new_image);
        newTitle = addItemDialog.findViewById(R.id.new_title);
        newDescription = addItemDialog.findViewById(R.id.new_description);
        saveButton = addItemDialog.findViewById(R.id.save_button);

        newImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStoragePermissionGranted(MainActivity.this)){
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, 12);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 8);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog.dismiss();
            }
        });

        addItemDialog.setTitle("Adicionar novo item");
        addItemDialog.show();
    }

    public static boolean isStoragePermissionGranted(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK){
            Uri uri = data.getData();
            path = getRealPathFromURI(uri);
            File f = new File(path);
            Picasso.get().load(f).into(newImageView);
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        String result = cursor.getString(idx);
        cursor.close();
        return result;
    }
}