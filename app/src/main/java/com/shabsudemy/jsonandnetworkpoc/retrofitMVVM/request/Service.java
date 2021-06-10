package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.request;

import android.graphics.Movie;
import android.net.wifi.hotspot2.pps.Credential;

import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.Credentials;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static  Retrofit.Builder retrofirBuilder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static  Retrofit retrofit = retrofirBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMoviewApi(){
        return movieApi;
    }
}
