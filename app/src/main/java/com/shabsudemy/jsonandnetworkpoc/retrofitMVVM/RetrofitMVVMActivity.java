package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shabsudemy.jsonandnetworkpoc.R;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.Movie;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.MovieDetails;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.MovieResponse;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.request.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitMVVMActivity extends AppCompatActivity {
    Button button1;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_mvvmactivity);
        button1 = findViewById(R.id.moviebutton1);
        editText = findViewById(R.id.movieEditText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(editText.getText().length() <= 0)) {
//                    getMovieList(editText.getText().toString());
//                    getMovieList("Batman");

                    getMovieDetailById("tt0080684");
                }else{
                    Toast.makeText(RetrofitMVVMActivity.this, "place enter movie/series name", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getMovieList(String movieText) {
        MovieApi movieApi = Service.getMoviewApi();
        //        s=Batman
        Call<MovieResponse> responseCall = movieApi.getMovie(movieText, Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d("TAG", call.request().url().toString()); ////to print generated url
                Log.d("TAG",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<Movie> movieList = response.body().getMovieList();
                    if(movieList!=null && movieList.size()>0){
                        for (Movie movie : movieList) {
                            Log.d("TAG", movie.getTitle());
                        }
                        Toast.makeText(RetrofitMVVMActivity.this, String.valueOf(movieList.size()), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("TAG", call.request().url().toString()); //to print generated url
                Log.d("TAG", t.getMessage().toString());
                Log.d("TAG", " call failed");
            }
        });
    }

    private  void  getMovieDetailById(String movieId){
        MovieApi movieApi = Service.getMoviewApi();
        Call<MovieDetails> movieDetailsCall = movieApi.getMovieDetails(movieId,Credentials.API_KEY);
        movieDetailsCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                Log.d("TAG",String.valueOf(response.body().getRatings().get(0).getValue()));
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.d("TAG", call.request().url().toString()); //to print generated url
                Log.d("TAG", t.getMessage().toString());
                Log.d("TAG", " call failed");

            }
        });
    }
}