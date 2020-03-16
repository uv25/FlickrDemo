package com.example.flickrdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class dialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        getIncomingIntent();
    }

    private void getIncomingIntent()
    {
        if (getIntent().hasExtra("image_url"))
        {
            String image_url = getIntent().getStringExtra("image_url");
            setImage(image_url);
        }
    }

    private void setImage(String imageUrl)
    {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }
}
