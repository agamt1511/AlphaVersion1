package com.example.alphaversion1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity7 extends AppCompatActivity {
    int SELECT_PICTURE = 200;
    String name;

    Uri selectedImageUri;

    Intent si;
    int CAMERA_PERM_CODE, CAMERA_REQUEST_CODE;

    ImageView imageView;
    Button camera_button;

    FirebaseStorage storage;
    StorageReference storageReference, ref, copyref;

    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        CAMERA_PERM_CODE = 101;
        CAMERA_REQUEST_CODE = 102;

        camera_button = (Button) findViewById(R.id.camera_button);
        imageView = (ImageView) findViewById(R.id.taken);

        // handle the Choose Image button to trigger the image chooser function
        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermissions();
            }
        });

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/jpeg");
        i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivity(Intent.createChooser(i, "Select Picture"));


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == SELECT_PICTURE) && (null != data)) {
            selectedImageUri = data.getData();
            uploadImage();
            loadFile();
        }
    }

    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }
        else{
            imageChooser();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            imageChooser();
        } else {
            Toast.makeText(this, "Camera Permission is required to use the camera!", Toast.LENGTH_LONG).show();
        }
    }

    void uploadImage() {
        if (selectedImageUri != null) {
            // Defining the child of storageReference
            name = "images/albert.jpg";
            ref = storageReference.child(name);

            ref.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    flag = true;
                }
            });
        }
    }

    private void loadFile(){
        try {
            copyref = storageReference.child(name);
            File local_file = File.createTempFile("tempFile",".jpg");
            copyref.getFile(local_file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(local_file.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.activity) {
            si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (id == R.id.activity2) {
            si = new Intent(this, MainActivity2.class);
            startActivity(si);
        }
        if (id == R.id.activity3) {
            si = new Intent(this, MainActivity3.class);
            startActivity(si);
        }
        if (id == R.id.activity4) {
            si = new Intent(this, MainActivity4.class);
            startActivity(si);
        }
        if (id == R.id.activity5) {
            si = new Intent(this, MainActivity5.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}
