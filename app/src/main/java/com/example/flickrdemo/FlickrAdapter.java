package com.example.flickrdemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.zip.Inflater;

public class FlickrAdapter extends RecyclerView.Adapter<FlickrAdapter.FlickrViewHolder> {

    private Context context;
    ImageView imageView;
    private Pic[] data;
    public FlickrAdapter(Context context, Pic[] data)
    {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public FlickrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        return new FlickrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrViewHolder holder, int position) {
        final Pic pic = data[position];

        Glide.with(holder.imageView.getContext()).load(pic.getURL()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, dialog.class);
                intent.putExtra("image_url", pic.getURL());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class FlickrViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public FlickrViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
