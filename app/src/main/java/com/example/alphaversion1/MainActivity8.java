package com.example.alphaversion1;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//***להוסיף הרשאות בManifest
//מסך - העלאת תמונה ממצלמה
public class MainActivity8 extends AppCompatActivity {

    //הגדרת משתנים
    int CAMERA_PERM_CODE, CAMERA_REQUEST_CODE;

    boolean photoMade;

    String currentPhotoPath, name;

    Uri photoURI;

    Intent si;

    ImageView iv;

    File f;

    FirebaseStorage storage;
    StorageReference storageReference, ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        CAMERA_PERM_CODE = 101;
        CAMERA_REQUEST_CODE = 102;
        photoMade = false;

        //שיוך משתנים למזהים בXML
        iv = (ImageView) findViewById(R.id.taken);

        //קריאה לFIREBASE Storage
        storage = FirebaseStorage.getInstance();

        //קבלת הפנייה לממסד נתונים
        storageReference = storage.getReference();
    }

    public void takePhoto(View view){
        askCameraPermissions();
    }

    //בקשת הרשאות מצלמה
    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        { ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);}
        else{ dispatchTakePictureIntent(); }
    }

    //אם ההרשאה לא התקבלה
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { dispatchTakePictureIntent();}
        else { Toast.makeText(this, "Camera Permission is required to use the camera!", Toast.LENGTH_LONG).show();}
    }

    //פעולה: הפעלת Intent לצילום תמונה
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            }
            catch (IOException ex){}

            if (photoFile!= null){
                //לשנות לפי שם האפליקציה את כתובת הURI
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.alphaversion1.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
                photoMade = true;
            }
        }
    }

    //פעולה: יצירת קובץ חדש לאחסון תמונה
    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd__HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFL = File.createTempFile(imageFileName, " .jpg", storageDir);
       //שמירת כתובת קובץ שנשמר
        currentPhotoPath = imageFL.getAbsolutePath();
        return imageFL;
    }

    //onActivityResult: יצירת קובץ והצגת תמונה באפליקצייה
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAMERA_REQUEST_CODE) && (resultCode == RESULT_OK) && (null != data)) {
                f = new File(currentPhotoPath); //יצירת קובץ חדש בהפניה
                iv.setImageURI(Uri.fromFile(f));
                uploadImage();
        }
    }


    //פעולה: העלאת קובץ מהאפליקציה לStorage
    void uploadImage() {
        if (Uri.fromFile(f) != null) {
            /* images - שם ההפניה שבתוכה שמים את את התמונה
               albert234 - שם הקובץ
               jpg - סוג הקובץ בו נשמרת התמונה*/
            name = "images/albert234.jpg";
            ref = storageReference.child(name); //הפנייה למיקום הילד
            ref.putFile(Uri.fromFile(f)); //העלאת הקובץ למיקום הרצוי
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
        if (id == R.id.activity) {
            si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        else if (id == R.id.activity2) {
            si = new Intent(this, MainActivity2.class);
            startActivity(si);
        }
        else if (id == R.id.activity3) {
            si = new Intent(this, MainActivity3.class);
            startActivity(si);
        }
        else if (id == R.id.activity4) {
            si = new Intent(this, MainActivity4.class);
            startActivity(si);
        }
        else if (id == R.id.activity5) {
            si = new Intent(this, MainActivity5.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

}
