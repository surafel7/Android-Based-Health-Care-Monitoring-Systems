package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectMeal extends AppCompatActivity {

    private CustomFoodListAdapter adapter;
    private List<ExampleItem> exampleList;
   // SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayList<AdapterItems> listNewData = new ArrayList<AdapterItems>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_meal);



        //list = new ArrayList<String>();

//        list.add("Enjera");
//        list.add("Aynet");
//        list.add("Burger");
//        list.add("Sandwich");
//        list.add("Shrowet");
//        list.add("tibs");
//        list.add("minchet");


       // adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        fillExampleList();
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        //searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // adapter.getFilter().filter(s);
                SelectMeal.this.adapter.getFilter().filter(s);
                return false;
            }
        });
        setUpRecyclerView();

    }
    private void fillExampleList() {
        exampleList = new ArrayList<>();

        exampleList.add(new ExampleItem("130 kcl", "Enjera","serving 1"));
        exampleList.add(new ExampleItem("130 kcl", "Doro","serving 1"));
        exampleList.add(new ExampleItem("130 kcl", "Kinche","serving 1"));
        exampleList.add(new ExampleItem("130 kcl", "Pasta","serving 1"));
        exampleList.add(new ExampleItem("130 kcl", "cabbage","serving 1"));
        exampleList.add(new ExampleItem("130 kcl", "apple","serving 1"));
        exampleList.add(new ExampleItem("130 kcl", "food","serving 1"));



    }
    private void setUpRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new CustomFoodListAdapter(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.example_menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.search_view);
//        SearchView searchView = (SearchView) findViewById(R.id.search_view);
//        //searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                // adapter.getFilter().filter(s);
//                SelectMeal.this.adapter.getFilter().filter(s);
//                return false;
//            }
//        });
        return true;
    }

    public void setValues(){
        String x = "abebe";
        String y = "abebe";
        String z = "abebe";
    }


}