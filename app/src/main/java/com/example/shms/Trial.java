package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Trial extends AppCompatActivity {

    ListView listView;
    String[] foodName = {"foodOne","foodOne","foodOne","foodOne","foodOne","foodOne"};
    String[] foodType = {"foodOne","foodOne","foodOne","foodOne","foodOne","foodOne"};
    String[] serving = {"1","1","1","1","1"};
    Integer[] imageId = {R.drawable.im1,R.drawable.im1,R.drawable.im1,R.drawable.im1,R.drawable.im1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        listView = (ListView) findViewById(R.id.mylistview);
        CustomListView customListView = new CustomListView(this,foodName,foodType,serving,imageId);
        listView.setAdapter(customListView);
    }
}