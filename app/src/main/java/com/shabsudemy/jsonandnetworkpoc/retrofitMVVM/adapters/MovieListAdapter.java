package com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shabsudemy.jsonandnetworkpoc.R;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.models.Movie;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    Context context;
    List<Movie> movieList;
    private OnMovieListener onMovieListener;

    public MovieListAdapter(Context context, List<Movie> movieList, OnMovieListener onMovieListener) {
        this.context = context;
        this.movieList = movieList;
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false);
        return new MyViewHolder(v, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        ((MyViewHolder) holder).ratingBar.setRating(3);
        ((MyViewHolder) holder).title.setText(movieList.get(position).getTitle());
        ((MyViewHolder) holder).imdId.setText(movieList.get(position).getImdbID());
        ((MyViewHolder) holder).year.setText(movieList.get(position).getYear());

        Glide.with(holder.itemView.getContext()).load(movieList.get(position).getPosterURL()).into(((MyViewHolder) holder).posterImage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView posterImage;
        TextView title, imdId, year;
        RatingBar ratingBar;

        OnMovieListener onMovieListener;

        public MyViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            title = itemView.findViewById(R.id.listItemTitle);
            imdId = itemView.findViewById(R.id.listItemImdbid);
            year = itemView.findViewById(R.id.listItemYear);
            ratingBar = itemView.findViewById(R.id.listItemRatingbar);
            posterImage = itemView.findViewById(R.id.listItemImage);
            this.onMovieListener = onMovieListener;
        }

        @Override
        public void onClick(View v) {
            onMovieListener.onMovieClick(getAdapterPosition());
        }
    }
}
