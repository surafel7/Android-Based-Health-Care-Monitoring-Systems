package com.example.shms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HealthBlog extends AppCompatActivity {
    List<Blog> otherBlogs;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private Toolbar toolbar;
    public  String userId;
    private DatabaseReference mDatabaseReff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_blog);
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
//        userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        toolbar = (Toolbar) findViewById(R.id.health_blog_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mDatabaseReff = FirebaseDatabase.getInstance().getReference("Blog").child("post");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary1));
        }

        otherBlogs = new ArrayList<>();
        GridLayoutManager layoutManager=new GridLayoutManager(HealthBlog.this,2);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        //((LinearLayoutManager) layoutManager).setStackFromEnd(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.health_recycler_view);
        recyclerView.setLayoutManager((layoutManager));
        mDatabaseReff = FirebaseDatabase.getInstance().getReference("Blog").child("post");
        mDatabaseReff.orderByChild("uid").equalTo("post").addListenerForSingleValueEvent(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        otherBlogs.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            //TODO get the data here
                            //  searchStorOnline s = dataSnapshot.getValue(searchStorOnline.class);
                            Blog req = postSnapshot.getValue(Blog.class);
                            otherBlogs.add(req);

                            RecycleViewAdapter       rViewAdapter = new RecycleViewAdapter( HealthBlog.this,otherBlogs);
                            recyclerView.setAdapter(rViewAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//
//        final DocumentReference documentReference1 = firebaseFirestore.collection("users").document(userId);
//        documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists()){
//                    String  title = documentSnapshot.getString("title");
//                    String catagory = documentSnapshot.getString("catagory");
//                    String blog = documentSnapshot.getString("blog");
//                    String imageLink = documentSnapshot.getString("imageLink");
//                    otherBlogs = new ArrayList<>();
//                    otherBlogs.add(new Blog(title,catagory,blog,imageLink,));
//                    RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.health_recycler_view);
//                    RecycleViewAdapter myViewAdapter = new RecycleViewAdapter(HealthBlog.this,otherBlogs);
//                    recyclerView1.setLayoutManager(new GridLayoutManager(HealthBlog.this,2));
//                    recyclerView1.setAdapter(myViewAdapter);
//                }else{
//                    Toast.makeText(HealthBlog.this,"User Does not exist",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(HealthBlog.this,"Error",Toast.LENGTH_SHORT);
//            }
//        });


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