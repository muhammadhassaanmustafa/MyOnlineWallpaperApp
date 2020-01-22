package com.hmdevs.onlinewallpaperapp.Helper;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.widget.Toast;

import java.lang.annotation.ElementType;
import java.lang.ref.WeakReference;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class SaveImageHelper implements Target {

    Context context;
    WeakReference<AlertDialog>  alertDialogWeakReference;
    WeakReference<ContentResolver> contentResolverWeakReference;
    String name,description;



    public SaveImageHelper(Context context, AlertDialog alertDialog, ContentResolver contentResolver, String name, String description)
    {
        this.context=context;
        alertDialogWeakReference= new WeakReference<AlertDialog>(alertDialog);
        contentResolverWeakReference= new WeakReference<ContentResolver>(contentResolver);
        this.name= name;
        this.description= description;
    }




    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

        Toast.makeText(context,"Downloading", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        ContentResolver c= contentResolverWeakReference.get();
        AlertDialog a =alertDialogWeakReference.get();
        if(c!=null)
        {
            MediaStore.Images.Media.insertImage(c,bitmap,name,description);
            a.dismiss();
            Toast.makeText(context, "Image Downloaded Successfully",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
