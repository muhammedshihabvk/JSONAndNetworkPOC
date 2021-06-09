package com.shabsudemy.jsonandnetworkpoc.retrofitsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.shabsudemy.jsonandnetworkpoc.R;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    Context context;
    List<DataModelAlbum> dataModelAlbumList;

    public AlbumAdapter(Context context, List<DataModelAlbum> dataModelAlbumList) {
        this.context = context;
        this.dataModelAlbumList = dataModelAlbumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.retrofit_sample_rv_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewId.setText(String.valueOf(dataModelAlbumList.get(position).getId()));
        holder.textViewTitle.setText(dataModelAlbumList.get(position).getTitle());

        Glide.with(context).asBitmap().load("https://assets.ajio.com/medias/sys_master/root/h9c/h37/15678460395550/-1117Wx1400H-461001523-multi-MODEL.jpg").into(holder.imageView);
        //        "https://assets.ajio.com/medias/sys_master/root/h9c/h37/15678460395550/-1117Wx1400H-461001523-multi-MODEL.jpg"
//
//        Glide.with(context)
//                .asBitmap()
//                .apply(RequestOptions.circleCropTransform())
//                .load(dataModelAlbumList.get(position).getSmallUrl())
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataModelAlbumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView textViewTitle, textViewId;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.retroRowSampleTitle);
            textViewId = itemView.findViewById(R.id.retroRowSampleId);
            imageView = itemView.findViewById(R.id.retroRowSampleImageView);
        }
    }
}
