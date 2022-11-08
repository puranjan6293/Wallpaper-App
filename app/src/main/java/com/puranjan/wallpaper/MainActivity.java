package com.puranjan.wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //initialising the variables
    private ArrayList<imageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mNature, mAnime, mCar, mLove, mTrending;
    EditText editText;
    ImageButton search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assigning xml id's in to our java id's
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclerView);
        mNature = findViewById(R.id.nature);
        mAnime = findViewById(R.id.anime);
        mCar = findViewById(R.id.car);
        mLove = findViewById(R.id.love);
        mTrending = findViewById(R.id.trending);

        editText = findViewById(R.id.editText);
        search = findViewById(R.id.search);

        //arraylist for model class type
        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), modelClassList);
        recyclerView.setAdapter(adapter);
        
        //by default the application shows the trending photos
        findPhotos();

        //if someone click on nature
        mNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query ="nature";
                getSearchImage(query);
            }
        });
        mAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query ="anime";
                getSearchImage(query);
            }
        });
        mCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query ="car";
                getSearchImage(query);
            }
        });
        mLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query ="love";
                getSearchImage(query);
            }
        });
        //we want to show trending photos by default
        mTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotos();
            }
        });

        //search
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    Toast.makeText(getApplicationContext(), "enter something", Toast.LENGTH_SHORT).show();
                }
                else{
                    getSearchImage(query);
                }
            }
        });

    }

    //here we have to call api utilities
    private void getSearchImage(String query) {
        apiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<searchModel>() {
            @Override
            public void onResponse(Call<searchModel> call, Response<searchModel> response) {
                //after getting the response from the api we have to clear the list
                //and we have to load the new data
                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<searchModel> call, Throwable t) {

            }
        });
    }

    private void findPhotos() {
        modelClassList.clear();
        apiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<searchModel>() {
            @Override
            public void onResponse(Call<searchModel> call, Response<searchModel> response) {
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<searchModel> call, Throwable t) {

            }
        });
    }
}