package com.puranjan.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class setWalpaper extends AppCompatActivity {
    Intent intent;
    ImageView imageView;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_walpaper);

        //remove the action bar
        getSupportActionBar().hide();
        //remove the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        set = findViewById(R.id.set);
        imageView = findViewById(R.id.finalImage);
        intent = getIntent();

        String url = intent.getStringExtra("image");
        //then we have to load this
        Glide.with(getApplicationContext()).load(url).into(imageView);
        //for setting wallpaper
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}