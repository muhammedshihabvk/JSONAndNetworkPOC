package com.shabsudemy.jsonandnetworkpoc.JSONToRV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shabsudemy.jsonandnetworkpoc.R;
import com.shabsudemy.jsonandnetworkpoc.models.Album;

import java.util.List;

public class JSONTORVAdapter extends RecyclerView.Adapter<JSONTORVAdapter.MyViewHolder> {

    Context context;
    private List<Album> dataModelsList;

    public JSONTORVAdapter(Context context, List<Album> dataModelsList) {
        this.context = context;
        this.dataModelsList = dataModelsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.json_to_rv_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JSONTORVAdapter.MyViewHolder holder, int position) {
        holder.bindData(dataModelsList.get(position));
        Glide.with(context).load("https://cdn.pixabay.com/photo/2018/02/06/08/04/orange-3134148_960_720.png").placeholder(R.drawable.ic_launcher_foreground).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return dataModelsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView largeText,smallText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.jsonToRvRowImage);
            largeText = itemView.findViewById(R.id.jsonToRvRowlargeText);
            smallText = itemView.findViewById(R.id.jsonToRvRowSmallText);
        }

        public void bindData(Album album){
            largeText.setText(album.getTitle());
            smallText.setText(String.valueOf(album.getId()));
        }
    }
}
