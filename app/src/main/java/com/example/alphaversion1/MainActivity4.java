package com.example.alphaversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity4 extends AppCompatActivity {
    String st_tit, st_txt, name;

    byte[] txt_byte;

    Intent si;

    EditText tit, txt;

    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tit = (EditText) findViewById(R.id.tit);
        txt = (EditText) findViewById(R.id.txt);
    }

    public void upload(View view) {
        st_tit = tit.getText().toString();
        st_txt = txt.getText().toString();

        txt_upload();
    }

    private void txt_upload() {
        name = "txt/"+st_tit+".txt";
        ref = FirebaseStorage.getInstance().getReference().child(name);
        txt_byte = st_txt.getBytes();
        ref.putBytes(txt_byte);
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
        if (id==R.id.activity2){
            si = new Intent(this, MainActivity2.class);
            startActivity(si);
        }
        if (id==R.id.activity3){
            si = new Intent(this, MainActivity3.class);
            startActivity(si);
        }
        if (id==R.id.activity5){
            si = new Intent(this, MainActivity5.class);
            startActivity(si);
        }
        if (id == R.id.activity8) {
            si = new Intent(this, MainActivity8.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}