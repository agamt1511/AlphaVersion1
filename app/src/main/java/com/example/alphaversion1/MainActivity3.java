package com.example.alphaversion1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.EditText;
import java.util.Calendar;

//מסך - יצירת התראה
public class MainActivity3 extends AppCompatActivity {
    //הגדרת משתנים
    String st1;

    Intent si;

    EditText et1;

    Button notifyNo_btn, notifyYes_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //שיוך משתנים למזהים בXML
        et1 = (EditText) findViewById(R.id.et1);
        notifyNo_btn = (Button) findViewById(R.id.notifyNo_btn);
        notifyYes_btn = (Button) findViewById(R.id.notifyYes_btn);

        //יצירת מאזין ל-notifyNo_btn
        notifyNo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNotify_NoTimer();
            }
        });
    }

    //פעולה: יצירת התראה ללא טיימר
    public void CreateNotify_NoTimer() {
        st1 = et1.getText().toString();
        //יצירת ערוץ
        createNotificationChannel();

        //תוכן ההתראה
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notify_NoTimer")
                .setContentText(st1)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity3.this);

        //יצירת int ייחודי עבור כל התראה
        notificationManager.notify(100, builder.build());

    }

    //פעולה: יצירת בוחר זמן
    public void openTimePicker(View view) {
        //דוגמה של לוח השנה משלנו
        final Calendar c = Calendar.getInstance();

        //מקבל את השעה והדקות שלנו
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Time Picker Dialog-אתחול ומאזין של ה
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity3.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(MainActivity3.this, AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity3.this, 0, intent, 0);
                        Calendar time = Calendar.getInstance();
                        time.setTimeInMillis(System.currentTimeMillis());
                        time.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        time.set(Calendar.MINUTE, minute);
                        time.set(Calendar.SECOND, 0);
                        alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
                    }
                }, hour, minute, true);
        //הצגת ה-Time Picker Dialog
        timePickerDialog.show();
    }

    //פעולה: יצירת ערוץ התראות
    private void createNotificationChannel() {
        //יצירת NotificationChannel, אבל רק עבור API +26 אחרת ה-class לא תעבוד
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //הגדרת קבוע מזהה לערוץ, שם לערוץ ורמת חשיבות לערוץ
            CharSequence name = "Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            //רשימת הערוץ במערכת. לאחר מכאן אי אפשר לשנות רמת חשיבות או תכונות אחרות של הערוץ
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
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
        else if (id==R.id.activity2){
            si = new Intent(this, MainActivity2.class);
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