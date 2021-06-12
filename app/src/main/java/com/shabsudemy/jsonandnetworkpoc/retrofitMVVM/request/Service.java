package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.request;

import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.Credentials;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.MovieApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();


    private Retrofit.Builder retrofirBuilder = new Retrofit.Builder().baseUrl(Credentials.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client);


    private Retrofit retrofit = retrofirBuilder.build();

    private MovieApi movieApi = retrofit.create(MovieApi.class);

    public MovieApi getMoviewApi() {
        return movieApi;
    }
}
