package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.Credentials;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.Movie;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private static MovieApiClient instance;
    private MutableLiveData<List<Movie>> mMovies;

    RetriveMoviewRunnable retriveMoviewRunnable;

    private MovieApiClient() {
        mMovies = new MutableLiveData<List<Movie>>();
    }

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public void getMovieApi(String movieName) {
        if(retriveMoviewRunnable!=null){
            retriveMoviewRunnable=null;
        }

        retriveMoviewRunnable = new RetriveMoviewRunnable(movieName);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retriveMoviewRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
                retriveMoviewRunnable.cancelRequest();
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    private class RetriveMoviewRunnable implements Runnable {

        boolean cancelRequest;
        private String movieName;

        RetriveMoviewRunnable(String movieName) {
            this.movieName = movieName;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Log.d("TAG","Thread started");
                Response<MovieResponse> response = getMovie(movieName).execute();
                Log.d("TAG","Thread end");
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieResponse) response.body()).getMovieList());
                    mMovies.postValue(list);
                }

            } catch (Exception e) {
                Log.d("TAG","Exception triggerd on run");
                List<Movie> list = new ArrayList<>();
                mMovies.postValue(list);
                e.printStackTrace();
            }
        }

        private Call<MovieResponse> getMovie(String movieName) {
            return new Service().getMoviewApi().getMovie(movieName, Credentials.API_KEY);
        }

        private void cancelRequest() {
            Log.d("TAG", "Cancelling movie request");
            cancelRequest = true;
        }
    }

}
