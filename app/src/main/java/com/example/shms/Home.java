package com.example.shms;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Home extends AppCompatActivity {

    List<Blog> blogList;
    private DrawerLayout dl;
    private ActionBarDrawerToggle aBdT;
    private Toolbar toolbar;
    public double caloriePerDay;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private TextView txtView,consumedCal,leftCal,breakfast,lunch,dinner;
  public  String userId;
    private DatabaseReference mDatabaseReff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        String gender;
//        String birthdate;
//        int age ;
//        String weight;
//        double uWeight;
//        String height;
//        double uHeight;
//        String activityLevel;



        txtView = (TextView) findViewById(R.id.today_goal);
        consumedCal = (TextView) findViewById(R.id.consumed_calorie);
        leftCal = (TextView) findViewById(R.id.left_calorie);
        breakfast = (TextView) findViewById(R.id.breakfast_calorie);
        lunch = (TextView) findViewById(R.id.lunch_calorie);
        dinner = (TextView) findViewById(R.id.dinner_calorie);
        leftCal = (TextView) findViewById(R.id.left_calorie);
        String userId;
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary1));
        }
        dl =  findViewById(R.id.dl);
        aBdT = new ActionBarDrawerToggle(this,dl,toolbar,R.string.open,R.string.close);

       if (dl != null) {
            dl.addDrawerListener(aBdT);
        }

        aBdT.setDrawerIndicatorEnabled(true);
        aBdT.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(Home.this.getApplicationContext(), Home.class);
                    Home.this.startActivity(intent);
                }
                if (id == R.id.profile) {
                    Intent intent = new Intent(Home.this.getApplicationContext(), UserProfile.class);
                    Home.this.startActivity(intent);
                }
                if (id == R.id.my_diet) {
                    Intent intent = new Intent(getApplicationContext(),food.class);
                    startActivity(intent);
                }
                if (id == R.id.activity) {
                    Intent intent = new Intent(getApplicationContext(),MyActivity.class);
                    startActivity(intent);
                }

                if (id == R.id.health_blog) {
                    Intent intent = new Intent(getApplicationContext(), HealthBlog.class);
                    startActivity(intent);
                }
                if (id == R.id.Add_Blog) {
                 String   userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

                    final DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                String UserType = documentSnapshot.getString("User");
                                if (UserType.matches("Admin")){
                                    Intent intent = new Intent(getApplicationContext(), AddBlog.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),"You can not post Blog this is for Admin Only",Toast.LENGTH_SHORT).show();
                                }

                            }else{
                                Toast.makeText(getApplicationContext(),"User Does not exist",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                        }
                    });
                }
                if(id == R.id.about){
                    Intent intent = new Intent(getApplicationContext(), AboutUs.class);
                    startActivity(intent);
                }
                if(id == R.id.feedback){
                    String   userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

                    final DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists()){
                                String UserType = documentSnapshot.getString("User");
                                if (UserType.matches("Admin")){
                                    Intent intent = new Intent(getApplicationContext(), ViewFeedBack.class);
                                    startActivity(intent);
                                }else{
                                    startActivity(new Intent(Home.this,FeedBack.class));
                                    finish();
                                }

                            }else{
                                Toast.makeText(getApplicationContext(),"User Does not exist",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                        }
                    });

                }
                if(id == R.id.logout){
                    auth.getInstance().signOut();
                    startActivity(new Intent(Home.this,Login.class));
                    finish();
                }
            return true;}
        });


        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){

                    double uBreakfast = 0;
                    double uLunch = 0;
                    double uDinner = 0;
                    double calorieConsumed= 0;
                    double calorieLeft = 0;
                    String gender = documentSnapshot.getString("gender");
                    String birthdate = documentSnapshot.getString("birth");
                    int age = Integer.parseInt(birthdate);
                    String weight = documentSnapshot.getString("weight");
                    double uWeight = Double.parseDouble(weight);
                    String height = documentSnapshot.getString("height");
                    double uHeight = Double.parseDouble(weight);
                    String activityLevel = documentSnapshot.getString("activitylevel");
                    double calories = calculateRequiredCalories(gender,activityLevel,uHeight,uWeight,age);
                    //String truncated = String.valueOf((int) calories);
                    double x = roundTwoDecimals(calories);
                    String cal = Double.toString(x);
                    txtView.setText(cal);
                    //userAge.setText(birthdate);
                    //dashBoardUser.setText(fullName);
                    calorieConsumed = uBreakfast + uLunch + uDinner;
                    calorieLeft = x - calorieConsumed;

                    String calCon = Double.toString(calorieConsumed);
                    String calLeft = Double.toString(calorieLeft);
                    String calBr = Double.toString(uBreakfast);
                    String calDr = Double.toString(uDinner);
                    String calLc = Double.toString(uLunch);
                    consumedCal.setText(calCon);
                    leftCal.setText(calLeft);
                    breakfast.setText(calBr);
                    lunch.setText(calLc);
                    dinner.setText(calDr);
                }else{
                    Toast.makeText(Home.this,"User Does not exist",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Home.this,"Error",Toast.LENGTH_SHORT);
            }
        });
        blogList = new ArrayList<>();
        GridLayoutManager layoutManager=new GridLayoutManager(Home.this,2);
        ((LinearLayoutManager) layoutManager).setReverseLayout(true);
        //((LinearLayoutManager) layoutManager).setStackFromEnd(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager((layoutManager));
        mDatabaseReff = FirebaseDatabase.getInstance().getReference("Blog").child("post");
        mDatabaseReff.orderByChild("uid").equalTo("post").addListenerForSingleValueEvent(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        blogList.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            //TODO get the data here
                            //  searchStorOnline s = dataSnapshot.getValue(searchStorOnline.class);
                           Blog req = postSnapshot.getValue(Blog.class);
                            blogList.add(req);

                            RecycleViewAdapter       rViewAdapter = new RecycleViewAdapter( Home.this,blogList);
                            recyclerView.setAdapter(rViewAdapter);


                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//
//        final DocumentReference documentReference1 = firebaseFirestore.collection("users").document("post");
//        documentReference1.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if(documentSnapshot.exists()){
//                    String  title = documentSnapshot.getString("title");
//                    String catagory = documentSnapshot.getString("catagory");
//                    String blog = documentSnapshot.getString("blog");
//                    String imageLink = documentSnapshot.getString("imageLink");
//
//                    blogList.add(new Blog(title,catagory,blog,imageLink));
//                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_id);
//                    RecycleViewAdapter rViewAdapter = new RecycleViewAdapter(Home.this,blogList);
//                    recyclerView.setLayoutManager(new GridLayoutManager(Home.this,1));
//                    recyclerView.setAdapter(rViewAdapter);
//                }else{
//                    Toast.makeText(Home.this,"User Does not exist",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Home.this,"Error",Toast.LENGTH_SHORT);
//            }
//
//        });
    }



//    public static double calculateCalories(String gender,){
//
//    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)){
            dl.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }
    double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#");
        return Double.valueOf(twoDForm.format(d));
    }

    public double consumedCal(double breakfast,double lunch,double dinner){
        return breakfast + lunch + dinner;
    }
//    public double leftCal(){
//        return breakfast + lunch + dinner;
//    }
    public static double calculateRequiredCalories(String gender,String activityLevel,double height,double weight, int age){
        double calculatedBmr;
        double caloriePerDay;
        if(gender.equals("Male")){
            calculatedBmr = 66.5 + (13.7 * weight) + (5*height) - (6.76 * age);
            if(activityLevel.equals("Low")){
                caloriePerDay = calculatedBmr * 1.375;
            }
            else if(activityLevel.equals("Medium")){
                caloriePerDay = calculatedBmr * 1.55;
            } else {
                caloriePerDay = calculatedBmr * 1.725;
            }
        }else{
            calculatedBmr = 655 + (9.56 * weight) + (1.8 * height) - (4.68 * age);
            if(activityLevel.equals("Low")){
                caloriePerDay = calculatedBmr * 1.375;
            }
            else if(activityLevel.equals("Medium")){
                caloriePerDay = calculatedBmr * 1.55;
            } else {
                caloriePerDay = calculatedBmr * 1.725;
            }
        }
        return caloriePerDay;
    }

}
