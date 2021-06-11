package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.shabsudemy.jsonandnetworkpoc.R;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.Movie;

public class RetrofitMVVMMovieDetailsActivity extends AppCompatActivity {

    ImageView bgImage;
    TextView title, year;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_mvvmmovie_details);

        bgImage = findViewById(R.id.imageViewdetails);
        title = findViewById(R.id.textViewtitle);
        year = findViewById(R.id.textViewyear);
        ratingBar = findViewById(R.id.ratingBar);

        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            Movie movie = getIntent().getParcelableExtra("movie");
            Log.d("TAG", movie.getTitle());
            bindData(movie);
        }
    }

    private void bindData(Movie movie) {
        Glide.with(this).load(movie.getPosterURL()).into(bgImage);
        title.setText(movie.getTitle());
        year.setText("Year "+String.valueOf(movie.getYear()));
        ratingBar.setRating(3);
    }
}