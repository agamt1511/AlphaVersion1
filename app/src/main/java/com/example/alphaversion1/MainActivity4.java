package com.example.alphaversion1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

//***להוסיף הרשאות בManifest
//מסך - העלאת קובץ TXT
public class MainActivity4 extends AppCompatActivity {
    //הגדרת משתנים
    String st_tit, st_txt, name;

    byte[] txt_byte;

    Intent si;

    EditText tit, txt;

    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //שיוך משתנים למזהים בXML
        tit = (EditText) findViewById(R.id.tit);
        txt = (EditText) findViewById(R.id.txt);
    }

    // פעולה: המרת Text ל String
    public void upload(View view) {
        st_tit = tit.getText().toString();
        st_txt = txt.getText().toString();

        txt_upload();
    }

    //העלאת קובץ מהאפליקציה לStorage
    private void txt_upload() {
        /* txt - שם ההפניה שבתוכה שמים את את התמונה
           st_tit - שם הקובץ
           txt - סוג הקובץ בו נשמרת התמונה*/
        name = "txt/"+st_tit+".txt";
        ref = FirebaseStorage.getInstance().getReference().child(name); //הפנייה למיקום הילד
        txt_byte = st_txt.getBytes(); // מוריד את הנתונים במיקום האובייקט
        ref.putBytes(txt_byte); // העלאת הקובץ למיקום הרצוי
    }

    //תפריט הקשר
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
        else if (id==R.id.activity2){
            si = new Intent(this, MainActivity2.class);
            startActivity(si);
        }
        else if (id==R.id.activity3){
            si = new Intent(this, MainActivity3.class);
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