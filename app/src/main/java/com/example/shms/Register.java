package com.example.shms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText register_fullname,login_email,register_birth_date,register_weight,register_height,register_password,login_password;
    Button register;
    Toolbar toolbar;
    Spinner spinnerSelectActivity,spinnerSelectGender;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    String fullname,email,gender,birthdate,weight,height,password,confirmpassword,activityLevel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.reg_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();

        register_fullname=findViewById(R.id.register_fullname);
        login_email=findViewById(R.id.login_email);
        register_birth_date=findViewById(R.id.register_birth_date);
        register_weight=findViewById(R.id.register_weight);
        register_height=findViewById(R.id.register_height);
        register_password=findViewById(R.id.register_password);
        login_password=findViewById(R.id.login_password);
        register=findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname=register_fullname.getText().toString();
                email=login_email.getText().toString();
                gender=spinnerSelectGender.getSelectedItem().toString();
                birthdate=register_birth_date.getText().toString();
                weight=register_weight.getText().toString();
                height=register_height.getText().toString();
                password=register_password.getText().toString();
                confirmpassword=login_password.getText().toString();
                activityLevel = spinnerSelectActivity.getSelectedItem().toString();

                checkForm();

            }
        });


        spinnerSelectActivity = (Spinner) findViewById(R.id.activity_level_spinner);
        spinnerSelectGender = (Spinner) findViewById(R.id.select_gender_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_level_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSelectActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    // Notify the selected item text
                    //Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSelectGender.setAdapter(adapterGender);
        spinnerSelectGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    // Notify the selected item text
                    //Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSelectActivity.setAdapter(adapter);

    }


    private void checkForm() {

        if (fullname.isEmpty()){
            register_fullname.setError("Empty fullname!");
            register_fullname.requestFocus();
            return;
        }
        if (email.isEmpty()){
            register_fullname.setError("Empty email!");
            register_fullname.requestFocus();
            return;
        }
        if (gender.isEmpty()){
            register_fullname.setError("Empty gender!");
            register_fullname.requestFocus();
            return;
        }
        if (birthdate.isEmpty()){
            register_fullname.setError("Empty birthdate!");
            register_fullname.requestFocus();
            return;
        }
        if (weight.isEmpty()){
            register_fullname.setError("Empty weight!");
            register_fullname.requestFocus();
            return;
        }
        if (height.isEmpty()){
            register_fullname.setError("Empty height!");
            register_fullname.requestFocus();
            return;
        }
        if (password.isEmpty()){
            register_fullname.setError("Empty password!");
            register_fullname.requestFocus();
            return;
        }
        if (confirmpassword.isEmpty()){
            register_fullname.setError("Empty confirmation password!");
            register_fullname.requestFocus();
            return;
        }

        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                    String userId = auth.getCurrentUser().getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
                    Map<String,Object> user = new HashMap<>();
                    user.put("fullname",fullname);
                    user.put("email",email);
                    user.put("gender",gender);
                    user.put("birth",birthdate);
                    user.put("weight",weight);
                    user.put("height",height);
                    user.put("activitylevel",activityLevel);
                    user.put("User","User");
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Register.this,"Data stored",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this,"Error"+e.toString(),Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    Toast.makeText(Register.this,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
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
