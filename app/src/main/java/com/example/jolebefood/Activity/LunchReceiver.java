package com.example.jolebefood.Activity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.jolebefood.R;

public class LunchReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "my_firebase_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        // Lấy thông tin từ Intent
        String notificationTitle = intent.getStringExtra("notification_title");
        String notificationContent = intent.getStringExtra("notification_content");

        Intent appIntent = new Intent(context, MainActivity.class);
        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                appIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Thêm FLAG_IMMUTABLE
        );
        // Tạo NotificationCompat.Builder để xây dựng thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Notification.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_km)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Lấy NotificationManagerCompat để hiển thị thông báo
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(getNotificationId(), builder.build());
    }

    private int getNotificationId() {
        return (int) System.currentTimeMillis(); // ID duy nhất dựa trên thời gian hiện tại
    }
}

