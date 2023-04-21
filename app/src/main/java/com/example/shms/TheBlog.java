package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TheBlog extends AppCompatActivity {

    private TextView blogTitle,blogDesc,blogCategory;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_blog);
        blogTitle = (TextView) findViewById(R.id.blg_title);
        blogDesc = (TextView) findViewById(R.id.blg_desc);
        blogCategory = (TextView) findViewById(R.id.blg_category);
        img = (ImageView) findViewById(R.id.blg_image);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String Category = intent.getExtras().getString("Category");
        String Image = intent.getExtras().getString("Image");

        blogTitle.setText(Title);
        blogDesc.setText(Description);
        blogCategory.setText(Category);
        Glide.with(getApplicationContext()).load(Image).into(img);

    }
}