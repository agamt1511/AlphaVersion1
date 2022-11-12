package com.example.alphaversion1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.BroadcastReceiver;
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
import android.os.SystemClock;
import android.view.View;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;


public class MainActivity3 extends AppCompatActivity {
    EditText et1;
    TimePicker timePicker;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    TimePickerDialog timePickerDialouge;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        et1 = findViewById(R.id.et1);
    }

    public void CreateNotify_NoTimer() {
        String st1 = et1.getText().toString();
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notify_NoTimer")
                .setContentText(st1)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(100, builder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel";
            String description = "Channel 1 try";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
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
        if (id==R.id.activity){
            si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (id==R.id.activity2){
            si = new Intent(this, MainActivity2.class);
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