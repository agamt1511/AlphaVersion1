package com.example.alphaversion1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity2 extends AppCompatActivity {
    int SELECT_PICTURE = 200;
    String name;

    Uri selectedImageUri;

    Intent si;

    Button btn_gallery;
    ImageView image_gallery;

    FirebaseStorage storage;
    StorageReference storageReference, ref, mImageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_gallery = (Button) findViewById(R.id.btn_gallery);
        image_gallery = (ImageView) findViewById(R.id.image_gallery);

        // handle the Choose Image button to trigger the image chooser function
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == SELECT_PICTURE) && (null != data)) {
            selectedImageUri = data.getData();
            uploadImage();
            retrieveImage();
        }
    }

    void uploadImage() {
        if (selectedImageUri != null) {
            // Defining the child of storageReference
            name = "images/albert.jpg";
            ref = storageReference.child(name);

            ref.putFile(selectedImageUri);
        }
    }

    void retrieveImage() {
        mImageRef = FirebaseStorage.getInstance().getReference("image/albert.jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        mImageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        DisplayMetrics dm = new DisplayMetrics();
                        getWindowManager().getDefaultDisplay().getMetrics(dm);

                        image_gallery.setMinimumHeight(dm.heightPixels);
                        image_gallery.setMinimumWidth(dm.widthPixels);
                        image_gallery.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.activity){
            si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (id==R.id.activity3){
            si = new Intent(this, MainActivity3.class);
            startActivity(si);
        }
        if (id==R.id.activity4){
            si = new Intent(this, MainActivity4.class);
            startActivity(si);
        }
        if (id==R.id.activity5){
            si = new Intent(this, MainActivity5.class);
            startActivity(si);
        }
        if (id==R.id.activity6){
            si = new Intent(this, MainActivity6.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}



