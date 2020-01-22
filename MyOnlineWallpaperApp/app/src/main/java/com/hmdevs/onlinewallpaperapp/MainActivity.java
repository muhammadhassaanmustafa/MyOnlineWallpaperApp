package com.hmdevs.onlinewallpaperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hmdevs.onlinewallpaperapp.CategoryViewHolder.CategoryViewHolder;
import com.hmdevs.onlinewallpaperapp.Model.CategoryItem;
import com.hmdevs.onlinewallpaperapp.Utils.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference categoryBackgroundReference;
    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder> adapter;
    WallpaperManager myWallpaperManager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        categoryBackgroundReference= FirebaseDatabase.getInstance().getReference().child("CategoryBackground");
        options=new FirebaseRecyclerOptions.Builder<CategoryItem>().setQuery(categoryBackgroundReference,CategoryItem.class).build();
        adapter=new FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder>(options)
        {

            @Override
            protected void onBindViewHolder(final CategoryViewHolder holder, final int position, final CategoryItem model)
            {
                Picasso.get().load(model.getImagelink()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                            Picasso.get().load(model.getImagelink()).error(R.drawable.ic_terrain).into(holder.imageView);
                    }
                });

                holder.textView.setText(model.getName());
                holder.imageView.setOnClickListener(new View.  OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.CATEGORY_ID=adapter.getRef(position).getKey();
                        Utils.CATEGORY_SELECTED=model.getName();
                        Intent i=new Intent(MainActivity.this,ListWallpaperActivity.class);
                        startActivity(i);
                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_item,parent,false);
                return new CategoryViewHolder(v);
            }
        };

        GridLayoutManager gridLayoutManager= new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
        {
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null)
        {
            adapter.stopListening();
        }
        super.onStop();
    }
}
