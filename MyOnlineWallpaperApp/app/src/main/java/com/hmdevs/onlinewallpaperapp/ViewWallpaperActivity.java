package com.hmdevs.onlinewallpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hmdevs.onlinewallpaperapp.Helper.SaveImageHelper;
import com.hmdevs.onlinewallpaperapp.Utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.UUID;

import okhttp3.internal.Util;

public class ViewWallpaperActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fabDownload, fabWallpaper;
    ImageView image;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wallpaper);


        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        initialize();




        Picasso.get().load(Utils.selected_wallpaper.getImageLink()).into(image);
        fabWallpaper.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Picasso.get().load(Utils.selected_wallpaper.getImageLink()).into(target);
                WallpaperManager myWallpaperManager= WallpaperManager.getInstance(getApplicationContext());
                Bitmap bm= Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.RGB_565);
                try {
                    myWallpaperManager.setBitmap(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });


        fabDownload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String filename= UUID.randomUUID().toString() + ".png";
                android.app.AlertDialog.Builder b= new AlertDialog.Builder(ViewWallpaperActivity.this);
                b.setMessage("Downloading..");
                android.app.AlertDialog alertDialog= b.create();
                alertDialog.show();
                Picasso.get().load(Utils.selected_wallpaper.getImageLink()).into(new SaveImageHelper(getBaseContext(),alertDialog,getApplicationContext().getContentResolver(),filename,"My Wallpaper"));
            }
        }
        );







    }

    private void initialize()
    {
        image= (ImageView)findViewById(R.id.thumbImage);
        collapsingToolbarLayout= (CollapsingToolbarLayout)findViewById(R.id.collapsingToolBarLayout);
        fabDownload=(FloatingActionButton)findViewById(R.id.fab_download);
        fabWallpaper=(FloatingActionButton)findViewById(R.id.fab_wallpaper);


        collapsingToolbarLayout.setTitle(Utils.CATEGORY_SELECTED);



    }



}
