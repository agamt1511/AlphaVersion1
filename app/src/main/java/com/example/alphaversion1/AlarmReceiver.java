package com.example.alphaversion1;
import static androidx.core.content.ContextCompat.getSystemService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //יצירת NotificationChannel, אבל רק עבור API +26 אחרת ה-class לא תעבוד
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //הגדרת קבוע מזהה לערוץ, שם לערוץ ורמת חשיבות לערוץ
            CharSequence name = "Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID1", name, importance);
            //רשימת הערוץ במערכת. לאחר מכאן אי אפשר לשנות רמת חשיבות או תכונות אחרות של הערוץ
            NotificationManager notificationManager = getSystemService(context, NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //תוכן ההתראה
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notify_Timer")
                .setContentText("Notify_WithTimer")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        //יצירת int ייחודי עבור כל התראה
        notificationManagerCompat.notify(200,builder.build());
    }
}