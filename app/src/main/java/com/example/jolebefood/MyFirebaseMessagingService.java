package com.example.jolebefood;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.jolebefood.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "my_firebase_channel"; // Thay thế bằng ID kênh mong muốn của bạn

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel(); // Tạo kênh thông báo khi dịch vụ được tạo
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name); // Cung cấp tên kênh thân thiện với người dùng từ strings.xml
            String description = getString(R.string.channel_description); // Cung cấp mô tả kênh

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Name", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Xử lý tin nhắn dữ liệu (nếu có)
        if (remoteMessage.getData().size() > 0) {

        } else {
            // Xử lý tin nhắn thông báo
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            // Tạo và hiển thị thông báo
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon_fb) // Giả sử icon_fb có trong tài nguyên drawable của bạn
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(101, builder.build());
        }
    }
}
