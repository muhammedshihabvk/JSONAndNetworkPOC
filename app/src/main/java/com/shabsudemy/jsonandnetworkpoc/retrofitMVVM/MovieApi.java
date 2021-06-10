package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM;

import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.MovieDetails;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    //    https://www.omdbapi.com/?s=star%20wars&apikey=132c797b
    @GET("/")
    Call<MovieResponse> getMovie(@Query("s") String movieNotation, @Query("apikey") String key);


    @GET("/")
    Call<MovieDetails> getMovieDetails(@Query("i") String movieImdbID, @Query("apikey") String key);
}
