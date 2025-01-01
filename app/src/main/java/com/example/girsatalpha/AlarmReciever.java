package com.example.girsatalpha;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReciever {
    private static final String CHANNEL_ID = "Your_Channel_ID";
    private static final String CHANNEL_NAME = "Your_Channel_Name";
    private static final int NOTIFICATION_ID = 1;

    public void onReceive(Context context, Intent ri) {
        String msg = ri.getStringExtra("msg");

    }
    public static void showNotification(Context context, String text) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder notiBbuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Time is over!!!")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(NOTIFICATION_ID, notiBbuilder.build());
    }
}
