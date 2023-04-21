package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewFeedBack extends AppCompatActivity {
RecyclerView recyclerView;
List<feed>list =new ArrayList<>();
    private DatabaseReference mDatabaseReff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed_back);
        recyclerView=findViewById(R.id.Rfeed);

        mDatabaseReff = FirebaseDatabase.getInstance().getReference("feedback");
        mDatabaseReff.orderByChild("key").equalTo("key").addListenerForSingleValueEvent(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            //TODO get the data here
                            //  searchStorOnline s = dataSnapshot.getValue(searchStorOnline.class);
                            feed req = postSnapshot.getValue(feed.class);
                            list.add(req);
                            FeedAdabter rViewAdapter = new FeedAdabter(ViewFeedBack.this,list);
                            recyclerView.setLayoutManager(new GridLayoutManager(ViewFeedBack.this,1));
                            recyclerView.setAdapter(rViewAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }
}