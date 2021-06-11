package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.Movie;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.repository.RetrofitMVVMRepository;

import java.util.List;

public class RetrofitMVVMViewModel extends ViewModel {

    //repository layer singleton instance
    private RetrofitMVVMRepository retrofitMVVMRepository;

    public RetrofitMVVMViewModel() {
        retrofitMVVMRepository = RetrofitMVVMRepository.getInstance();
    }

    public LiveData<List<Movie>> getmMovies() {
        return retrofitMVVMRepository.getMovies();
    }

    public  void getMovieCall(String  movieName){
        retrofitMVVMRepository.getMovieCall(movieName);

    }
}
