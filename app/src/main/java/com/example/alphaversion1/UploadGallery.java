package com.example.alphaversion1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;
import java.util.UUID;

public class UploadGallery extends AppCompatActivity {
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private ImageView iv;
    public Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_gallery);

        iv = (ImageView) findViewById(R.id.iv);

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    public void pickAnImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            iv.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        // Create a reference to "mountains.jpg"
        final String randomKey = UUID.randomUUID().toString();
        StorageReference mountainsRef = storageRef.child("images/" + randomKey);

        mountainsRef.putFile(imageUri);

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/" + randomKey);

        // While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        Toast.makeText(UploadGallery.this, "The picture has been uploaded", Toast.LENGTH_LONG).show();
    }


}