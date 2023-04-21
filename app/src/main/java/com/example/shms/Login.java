package com.example.shms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    public TextView registerLink, forgetPasswordLink, invalidPasswordOrEmail,forgetPssword;
    private Button loginButton;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            // startActivity(new Intent(this, splash.class));
//        }

        loginEmail = (EditText) findViewById(R.id.login_email);
        loginPassword = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);
        invalidPasswordOrEmail = (TextView) findViewById(R.id.invalid_email_or_password);
        forgetPssword=(TextView) findViewById(R.id.forget);
        forgetPasswordLink = (TextView) findViewById(R.id.forget_password);
        registerLink = (TextView) findViewById(R.id.create_new_account);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });
        forgetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
forgetPssword.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v) {
        auth.sendPasswordResetEmail(loginEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>(){
            @Override
            public void onComplete(@NonNull Task<Void> task){
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"reset Password link send by your email chech your email please!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Login.this,"check your emaile the right one!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(validateEmail() && validatePassword()) {
                //if(authenticateUser()) {

                String userEmail = loginEmail.getText().toString().trim();
                String userPassword = loginPassword.getText().toString().trim();

                if(userEmail.isEmpty()){
                    loginEmail.setError("email is required");
                    loginEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    loginEmail.setError("Please provide valid email");
                    loginEmail.requestFocus();
                    return;
                }
                if(userPassword.isEmpty()){
                    loginPassword.setError("Password is required");
                    loginPassword.requestFocus();
                    return;
                }
                if(userPassword.length() < 6 ){
                    loginPassword.setError("Minimum password length should be 6 characters");
                    loginPassword.requestFocus();
                    return;
                }


                auth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Login.this,"Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                //}
                // }
            }
        });
    }

    private boolean validatePassword() {
        String passwordInput = loginPassword.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            loginPassword.setError("Field can't be empty");
            return false;
        } else if (passwordInput.length() < 8) {
            loginPassword.setError("Password length must be greater than 8");
            return false;
        } else {
            loginEmail.setError(null);
            return true;
        }
    }

}





