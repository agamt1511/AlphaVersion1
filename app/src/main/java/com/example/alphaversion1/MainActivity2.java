package com.example.alphaversion1;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.IOException;


//***להוסיף הרשאות בManifest
//מסך - העלאה והורדה של תמונה מגלריה
public class MainActivity2 extends AppCompatActivity {
    //הגדרת משתנים
    int SELECT_PICTURE = 200;

    String name;

    Uri selectedImageUri;

    Intent si;

    Button btn_gallery;
    ImageView image_gallery;

    FirebaseStorage storage;
    StorageReference storageReference, ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //שיוך משתנים למזהים בXML
        btn_gallery = (Button) findViewById(R.id.btn_gallery);
        image_gallery = (ImageView) findViewById(R.id.image_gallery);

        //יצירת מאזין ל-btn_gallery
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        //קריאה לFIREBASE Storage
        storage = FirebaseStorage.getInstance();

        //קבלת הפנייה לממסד נתונים
        storageReference = storage.getReference();
    }


    //פעולה: פתיחת מצלמה ובחירת תמונה
    private void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    //onActivityResult: של התמונה URI קבלת כתובת
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == SELECT_PICTURE) && (resultCode == RESULT_OK) && (null != data)) {
            selectedImageUri = data.getData();
            uploadImage();
            loadFile();
        }
    }

    //פעולה: העלאת קובץ מהאפליקציה לStorage
    void uploadImage() {
        if (selectedImageUri != null) {
            /* images - שם ההפניה שבתוכה שמים את את התמונה
               albert - שם הקובץ - בגלל שהשם אותו דבר מציג את התמונה הקודמת ורק אז הופך לתמונה חדשה
               jpg - סוג הקובץ בו נשמרת התמונה*/
            name = "images/43ad-999563-3f650f37d42c888.jpg";
            ref = storageReference.child(name); //הפנייה למיקום הילד
            ref.putFile(selectedImageUri); //העלאת הקובץ למיקום הרצוי
        }
    }

    //פעולה: הורדת קובץ מהStorage ושימוש באפליקציה
    private void loadFile(){
        try {
        /* ההפניה למיקום הקובץ שבו רוצים להשתמש - ref
        local_file - יצירת קובץ תבנית לאחסון התמונה */
            File local_file = File.createTempFile("tempFile",".jpg");
            ref.getFile(local_file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(local_file.getAbsolutePath());
                    image_gallery.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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



