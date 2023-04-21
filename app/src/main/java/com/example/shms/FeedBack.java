package com.example.shms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class FeedBack extends AppCompatActivity {
EditText feedback;
Button send;
    private StorageReference mStorageReff;
    public View V;

    private DatabaseReference mDatabaseReff;
    FirebaseAuth auth;
    String userId;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        toolbar = (Toolbar) findViewById(R.id.forget_passwordToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary1));
        }
feedback=findViewById(R.id.feedback);
send =findViewById(R.id.Fsend);

        mDatabaseReff = FirebaseDatabase.getInstance().getReference("feedback");

        auth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String uploadId = mDatabaseReff.push().getKey();
        feed stor = new feed(userId,feedback.getText().toString(),"key");
        mDatabaseReff.child(uploadId).setValue(stor);
        Toast.makeText(FeedBack.this,"successfuly inserted your feedback thank you!",Toast.LENGTH_SHORT).show();
feedback.setText("");
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

}