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
    //הגדרת משתנים
    String addEmail, addPassword;

    Intent si;

    EditText email, password;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //קריאה לFIREBASE Authentication
        mAuth = FirebaseAuth.getInstance();

        //שיוך משתנים למזהים בXML
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

    //פעולה: הרשמה לFIREBASE Authentication
    public void register(View view) {
        //קליטת הטקסט והפיכתו למשתנה מסוג String
        addEmail = email.getText().toString().trim();
        addPassword = password.getText().toString().trim();

        //יצירת חשבון חדש על ידי העברת כתובת הדוא"ל והסיסמה של המשתמש החדש
        mAuth.createUserWithEmailAndPassword(addEmail,addPassword)
                //יצירת מאזין - אם הושלם יצירת משתמש חדש במחלקה User
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // הוספת רשומה חדשה לבסיס הנתונים
                    User user = new User(addEmail,addPassword);
                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user);
                }
            }
        });
    }


    //יצירת תפריט הקשר
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
        else if (id==R.id.activity3){
            si = new Intent(this, MainActivity3.class);
            startActivity(si);
        }
        else if (id==R.id.activity4){
            si = new Intent(this, MainActivity4.class);
            startActivity(si);
        }
        else if (id==R.id.activity5){
            si = new Intent(this, MainActivity5.class);
            startActivity(si);
        }
        else if (id == R.id.activity8) {
            si = new Intent(this, MainActivity8.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}