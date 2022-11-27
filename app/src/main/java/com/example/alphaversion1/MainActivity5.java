package com.example.alphaversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity{
    String[] organs = new String[20];

    Intent si;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        for (int i = 1; i < 20; i++) {
            organs[i] = "";
        }

        lv = (ListView) findViewById(R.id.lv);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,organs);
        lv.setAdapter(adp);
    }

    public void toChangeValue(View view) {
        organs[5] = "YES";
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
        if (id==R.id.activity4){
            si = new Intent(this, MainActivity4.class);
            startActivity(si);
        }
        if (id==R.id.activity6){
            si = new Intent(this, MainActivity7.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}