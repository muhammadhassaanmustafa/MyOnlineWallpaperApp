package com.hmdevs.onlinewallpaperapp.CategoryViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hmdevs.onlinewallpaperapp.R;

public class WallpaperViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;


    public WallpaperViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=(ImageView)itemView.findViewById(R.id.imageViewWallpaper);
    }
}
