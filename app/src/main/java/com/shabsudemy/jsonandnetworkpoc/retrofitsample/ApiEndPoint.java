package com.shabsudemy.jsonandnetworkpoc.retrofitsample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    @GET(" /photos")
    Call<List<DataModelAlbum>> getAlbumData();


}
