package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class food extends AppCompatActivity {
TextView food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        food=(TextView) findViewById(R.id.foods);
        food.setText("\n" +
                "Under weight\n" +
                "for break fast\n" +
                "1.Homemade protein smoothies\n" +
                "2. Milk\n" +
                "3. Rice \n" +
                "\n" +
                "For lunch\n" +
                "4. Avocados\n" +
                "5. Red meats\n" +
                "6. Potatoes and starches \n" +
                "\n" +
                "For dinner\n" +
                "7. Dried fruit\n" +
                "8. Dark chocolate\n" +
                "9. Cheese\n" +
                "\n" +
                "\n" +
                "overweight\n" +
                "for break fast\n" +
                "1. Fish\n" +
                "2. Chicken\n" +
                "3. Beans \n" +
                "\n" +
                "for lunch\n" +
                "4. Watermelon\n" +
                "5. Broccoli\n" +
                "6. Salad \n" +
                "\n" +
                "for dinner\n" +
                "8. Grape fruit\n" +
                "9. Apples\n" +
                "\n" +
                "\n" +
                "Normal weight\n" +
                "for break fast\n" +
                "1. Avocado\n" +
                "2. Banans\n" +
                "3. Yougurt \n" +
                "\n" +
                "for lunch\n" +
                "4. spinach\n" +
                "2. Fish\n" +
                "5. Potatos \n" +
                "\n" +
                "For dinner\n" +
                "6. Shero wete\n" +
                "7. oatmeal\n" +
                "8. pasta and makoroni");
    }
}