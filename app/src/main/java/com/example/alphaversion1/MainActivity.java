package com.example.alphaversion1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    String addEmail, addPassword;

    Intent si;

    EditText email, password;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    public void register(View view) {
        addEmail = email.getText().toString().trim();
        addPassword = password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(addEmail,addPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(addEmail,addPassword);

                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user);
                }
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
        if (id==R.id.activity5){
            si = new Intent(this, MainActivity5.class);
            startActivity(si);
        }
        if (id==R.id.activity6){
            si = new Intent(this, MainActivity7.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}