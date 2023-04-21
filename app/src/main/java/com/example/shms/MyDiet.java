package com.example.shms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDiet extends AppCompatActivity {

    private Toolbar toolbar;
    private Button addBreakfast,addLunch,addDinner;
    public ArrayList<AdapterItems> listNewData = new ArrayList<AdapterItems>();
    ArrayList<AdapterItems> listNewData1 = new ArrayList<AdapterItems>();
    ArrayList<AdapterItems> listNewData2 = new ArrayList<AdapterItems>();
    MyCustomAdapter myAdapter,myAdapter1,myAdapter2;
    private ListView lvBreakFast,lvLunch,lvDinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary1));
        }


        Intent intent = getIntent();



        setContentView(R.layout.activity_my_diet);
        toolbar = (Toolbar) findViewById(R.id.forget_passwordToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        addBreakfast = (Button) findViewById(R.id.button_add_breakfast);
        addLunch = (Button) findViewById(R.id.button_add_lunch);
        addDinner = (Button) findViewById(R.id.button_add_dinner);

        lvBreakFast = (ListView) findViewById(R.id.list_view_breakfast);

        lvLunch = (ListView) findViewById(R.id.list_view_lunch);

        lvDinner = (ListView) findViewById(R.id.list_view_dinner);

        myAdapter = new MyCustomAdapter(listNewData);
        myAdapter1 = new MyCustomAdapter(listNewData1);
        myAdapter2 = new MyCustomAdapter(listNewData2);

        lvBreakFast.setAdapter(myAdapter);
        lvLunch.setAdapter(myAdapter1);
        lvDinner.setAdapter(myAdapter2);


        addBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectMeal.class);
                startActivity(intent);
//                String calorie = intent.getExtras().getString("calorie");
//                String name = intent.getExtras().getString("name");
//                String serving = intent.getExtras().getString("serving");
//               listNewData.add(new AdapterItems(calorie,name,serving));
//                myAdapter.notifyDataSetChanged();
            }
        });

        addLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNewData1.add(new AdapterItems("xxx","aynet","Home Made"));

                myAdapter1.notifyDataSetChanged();
            }
        });

        addDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listNewData2.add(new AdapterItems("xyz","burger","Home Made"));
                myAdapter2.notifyDataSetChanged();
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

public  class MyCustomAdapter extends BaseAdapter {

    public ArrayList<AdapterItems> listNewDataAdapter;

    public MyCustomAdapter(ArrayList<AdapterItems> listNewDataAdapter) {
        this.listNewDataAdapter = listNewDataAdapter;
    }

    @Override
    public int getCount() {
        return listNewDataAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mLayoutInflater = getLayoutInflater();
        View mView = mLayoutInflater.inflate(R.layout.layout_food_display, null);
        final AdapterItems s = listNewDataAdapter.get(position);
        //ImageView imgView = (ImageView) mView.findViewById(R.id.food_image);
        //imgView.setImageResource(s.ID);
        TextView textJobTitle = (TextView) mView.findViewById(R.id.food_name);
        textJobTitle.setText(s.jobTitle);
        textJobTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), s.jobTitle, Toast.LENGTH_SHORT).show();

            }
        });
        TextView textDesc = (TextView) mView.findViewById(R.id.food_type);
        textDesc.setText(s.description);
        return mView;
    }
}

}