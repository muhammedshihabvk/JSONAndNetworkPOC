package com.shabsudemy.jsonandnetworkpoc.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shabsudemy.jsonandnetworkpoc.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPOCActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_pocactivity);

        progressBar = findViewById(R.id.progresscircular);

        recyclerView = findViewById(R.id.retrofitSampleRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        String baseURL = "https://jsonplaceholder.typicode.com";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);


        Call<List<DataModelAlbum>> call = apiEndPoint.getAlbumData();
        call.enqueue(new Callback<List<DataModelAlbum>>() {
            @Override
            public void onResponse(Call<List<DataModelAlbum>> call, Response<List<DataModelAlbum>> response) {
                if(response.isSuccessful() && response.code()==200){
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG",response.body().get(0).getUrl());
                    albumAdapter = new AlbumAdapter(RetrofitPOCActivity.this,response.body());
                    recyclerView.setAdapter(albumAdapter);
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RetrofitPOCActivity.this, "done", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataModelAlbum>> call, Throwable t) {

            }
        });

        Toast.makeText(this, "end", Toast.LENGTH_SHORT).show();


    }
}