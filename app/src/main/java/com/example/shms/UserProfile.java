package com.example.shms;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.util.Objects;

public class UserProfile extends AppCompatActivity {

    public TextView userFullName,userEmail,BMI,userGender,userBirthDate,userWeight,userHeight,userActivityLevel,userAge;
    public TextView dashBoardUser;

    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userFullName = (TextView) findViewById(R.id.user_full_name);
        userEmail = (TextView) findViewById(R.id.user_email);
        userGender = (TextView) findViewById(R.id.user_gender);
        userBirthDate = (TextView) findViewById(R.id.user_birth_date);
        userWeight = (TextView) findViewById(R.id.user_weight);
        userHeight = (TextView) findViewById(R.id.user_height);
        userActivityLevel = (TextView) findViewById(R.id.user_activity_level);
        //userAge = (TextView) findViewById(R.id.user_age);
BMI=(TextView) findViewById(R.id.user_activity_BMI);
        dashBoardUser = (TextView) findViewById(R.id.dashBoardUser);
        String userId;
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        final DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String fullName = documentSnapshot.getString("fullname");
                    String email = documentSnapshot.getString("email");
                    String gender = documentSnapshot.getString("gender");
                    String birthdate = documentSnapshot.getString("birth");
                    int age = parseInt(birthdate);
                    System.out.print(age);
                  String weight = documentSnapshot.getString("weight");
                 String height = documentSnapshot.getString("height");
                    String activityLevel = documentSnapshot.getString("activitylevel");

                    userFullName.setText(fullName);
                    userEmail.setText(email);
                    userGender.setText(gender);
                    userBirthDate.setText(birthdate);
                    userWeight.setText( weight);
                    userHeight.setText( height);
                    userActivityLevel.setText(activityLevel);
                    //userAge.setText(birthdate);
                    //dashBoardUser.setText(fullName);
                    double PofH=Double.parseDouble(height)*Double.parseDouble(height);
                    //double wg=Double.parseDouble(weight);
                   double bmi= Double.parseDouble(weight)/PofH;
                   if (bmi>=24){
                       BMI.setText(new DecimalFormat("##.##").format(bmi) +""+ "Kg/m2 over Weight") ;
                   }else if(bmi<=18){
                       BMI.setText(new DecimalFormat("##.##").format(bmi) +""+ "Kg/m2  under Weight") ;
                   }else{
                       BMI.setText(new DecimalFormat("##.##").format(bmi) +""+ "Kg/m2 in good status") ;
                   }

                }else{
                    Toast.makeText(UserProfile.this,"User Does not exist",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserProfile.this,"Error",Toast.LENGTH_SHORT);
            }
        });

    }
}
