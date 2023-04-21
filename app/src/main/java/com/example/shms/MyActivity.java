package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class MyActivity extends AppCompatActivity {

    private TextView stepsView;
    private PieModel sliceGoal, sliceCurrent;
    private PieChart pieChart;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Intent intent = new Intent(BROADCAST_ACTION);

        setContentView(R.layout.activity_my_activity);

        stepsView = (TextView) findViewById(R.id.steps);

        toolbar = (Toolbar) findViewById(R.id.forget_passwordToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary1));
//        }

       // pieChart = (PieChart) findViewById(R.id.pie_chart);


//        sliceCurrent = new PieModel("", 70, Color.parseColor("#1984b4"));
//        pieChart.addPieSlice(sliceCurrent);

        // slice for the "missing" steps until reaching the goal
//        sliceGoal = new PieModel("", 30, Color.parseColor("#CC0000"));
//        pieChart.addPieSlice(sliceGoal);
//        pieChart.addPieSlice(new PieModel("",20,Color.parseColor("#ffcccc")));
//        pieChart.addPieSlice(new PieModel("", 15, Color.parseColor("#d9f2d9")));
//        pieChart.setDrawValueInPie(false);
//        pieChart.setUsePieRotation(true);
//        pieChart.startAnimation();
//        mPieChart.addPieSlice(new PieModel("Freetime", 15, Color.parseColor("#FE6DA8")));
//        mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
//        mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
//        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));
//
//        mPieChart.startAnimation();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}