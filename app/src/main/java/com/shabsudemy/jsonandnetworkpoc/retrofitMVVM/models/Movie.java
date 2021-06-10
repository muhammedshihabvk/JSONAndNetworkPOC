package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    //Model class for movie details

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private int year;
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Poster")
    @Expose
    private String posterURL;

    public Movie(String title, int year, String imdbID, String type, String posterURL) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.posterURL = posterURL;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        year = in.readInt();
        imdbID = in.readString();
        type = in.readString();
        posterURL = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(year);
        dest.writeString(imdbID);
        dest.writeString(type);
        dest.writeString(posterURL);
    }
}
