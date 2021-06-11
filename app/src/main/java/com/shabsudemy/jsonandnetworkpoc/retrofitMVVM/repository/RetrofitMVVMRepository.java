package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.Movie;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.request.MovieApiClient;

import java.util.List;

public class RetrofitMVVMRepository {

    private static RetrofitMVVMRepository instance;
    private MovieApiClient mMovieApiClient;

    private RetrofitMVVMRepository() {
        mMovieApiClient = MovieApiClient.getInstance();
    }

    public static RetrofitMVVMRepository getInstance() {
        if (instance == null) {
            instance = new RetrofitMVVMRepository();
        }
        return instance;
    }


    public LiveData<List<Movie>> getMovies() {
        return mMovieApiClient.getMovies();
    }

    public  void getMovieCall(String  movieName){
        mMovieApiClient.getMovieApi(movieName);

    }

}
