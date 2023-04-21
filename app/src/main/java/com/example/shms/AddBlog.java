package com.example.shms;

import static android.webkit.MimeTypeMap.getSingleton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddBlog extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
   public EditText title,catagory,blog;
    FirebaseAuth auth;
Button post;
ImageView imageView;
String Title,Catagory,Blog;
    private static final int PICK_IMAGE_REQUEST = 2;
    private Uri mImageUr;
    private StorageTask mUploadTask;
    private StorageReference mStorageReff;
    public View V;

    private DatabaseReference mDatabaseReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blog);
       post =(Button) findViewById(R.id.post);
        title=findViewById(R.id.Title);
        catagory=findViewById(R.id.Catagory);
        blog=findViewById(R.id.addblog);
imageView=findViewById(R.id.image);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mDatabaseReff = FirebaseDatabase.getInstance().getReference("Blog").child("post");

        mStorageReff = FirebaseStorage.getInstance().getReference("image)");
imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(AddBlog.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddBlog.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                openFileChoo();
            }
        }else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            if (ContextCompat.checkSelfPermission(AddBlog.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddBlog.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                openFileChoo();
            }
        }

    }
});
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title=title.getText().toString();
                Catagory=title.getText().toString();
                Blog=blog.getText().toString();
checkForm();
            }
        });
    }

    private void checkForm() {
        if (Title.isEmpty()) {
            title.setError("Empty title!");
            title.requestFocus();
            return;
        }
        if (Catagory.isEmpty()) {
            catagory.setError("Empty weight!");
            catagory.requestFocus();
            return;
        }
        if (Blog.isEmpty()) {
            blog.setError("Empty height!");
            blog.requestFocus();
            return;
        }
        Toast.makeText(AddBlog.this,"your data is posting......",Toast.LENGTH_SHORT).show();

        auth = FirebaseAuth.getInstance();
        StorageReference fileReferences = mStorageReff.child(System.currentTimeMillis()
                + "." + getFileExtension(mImageUr));
       mUploadTask = fileReferences.putFile(mImageUr)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //   mProgressBar.setProgress(0);
                            }
                        }, 500);

                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                String img = task.getResult().toString();
                                Calendar calendar = Calendar.getInstance();
                                final String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                String uploadId = mDatabaseReff.push().getKey();
                                Blog stor = new Blog(title.getText().toString(),catagory.getText().toString(),blog.getText().toString(),img,"post");
                                mDatabaseReff.child(uploadId).setValue(stor);
                                Toast.makeText(AddBlog.this,"successfuly inserted thank you!",Toast.LENGTH_SHORT).show();
                                title.setText("");
                                catagory.setText("");
                                blog.setText("");
//                                String userId = auth.getCurrentUser().getUid();
//                                DocumentReference documentReference = firebaseFirestore.collection("users").document("post").collection("Blog").document(System.currentTimeMillis()+userId);
//                                Map<String, Object> user = new HashMap<>();
//                                user.put("imageLink",img);
//                                user.put("title", Title);
//                                user.put("catagory", Catagory);
//                                user.put("blog", Blog);
//                                user.put("uid", auth.getCurrentUser().getUid());
//                                user.put("date", currentDate);
//                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        Toast.makeText(AddBlog.this,"Data stored",Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(AddBlog.this,"Error"+e.toString(),Toast.LENGTH_LONG).show();
//                                    }
//                                });


                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Snackbar.make(V, e.getMessage(), Snackbar.LENGTH_LONG).setAction("", null).show();

                    }
                });

        } private void openFileChoo() {
        Intent inten = new Intent();
        inten.setType("image/*");
        inten.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(inten, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null ) {
            mImageUr = data.getData();

            Picasso.get().load(mImageUr).into(imageView);
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime =getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}




