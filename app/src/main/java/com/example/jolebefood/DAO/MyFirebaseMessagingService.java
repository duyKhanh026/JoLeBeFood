package com.example.jolebefood.DAO;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.jolebefood.Activity.Cart;
import com.example.jolebefood.Activity.Discount;
import com.example.jolebefood.Activity.MainActivity;
import com.example.jolebefood.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "my_firebase_channel"; // Thay thế bằng ID kênh mong muốn của bạn
    private static final String CHANNEL_PRODUCT = "product_channel";
    private static final String CHANNEL_DISCOUNT = "discount_channel";
    private static final String CHANNEL_DELIVERY = "delivery_channel";

    public String title;
    public String message;

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

            NotificationChannel productChannel = new NotificationChannel(CHANNEL_PRODUCT, "Product Channel", NotificationManager.IMPORTANCE_HIGH);
            productChannel.setDescription("Channel for product notifications");

            // Kênh thông báo cho khuyến mãi
            NotificationChannel discountChannel = new NotificationChannel(CHANNEL_DISCOUNT, "Discount Channel", NotificationManager.IMPORTANCE_HIGH);
            discountChannel.setDescription("Channel for discount notifications");

            // Kênh thông báo cho giao hàng
            NotificationChannel deliveryChannel = new NotificationChannel(CHANNEL_DELIVERY, "Delivery Channel", NotificationManager.IMPORTANCE_HIGH);
            deliveryChannel.setDescription("Channel for delivery notifications");

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(productChannel);
            notificationManager.createNotificationChannel(discountChannel);
            notificationManager.createNotificationChannel(deliveryChannel);
        }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Xử lý tin nhắn thông báo
        title = remoteMessage.getNotification().getTitle();
        message = remoteMessage.getNotification().getBody();

        String channelId = determineChannelId(title);

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent secondaryIntent;
        if (channelId.equals(CHANNEL_DISCOUNT)) {
            secondaryIntent = new Intent(this, Discount.class);
        } else if (channelId.equals(CHANNEL_DELIVERY)) {
            secondaryIntent = new Intent(this, Cart.class);
        } else {
            secondaryIntent = mainIntent; // Hoặc Intent mặc định nếu không có kênh phù hợp
        }
        PendingIntent secondaryPendingIntent = PendingIntent.getActivity(this, 0, secondaryIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(mainIntent);
        stackBuilder.addNextIntent(secondaryIntent);

        PendingIntent fullPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Tạo và hiển thị thông báo
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.icon_km)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true);

        // Đặt PendingIntent tùy thuộc vào channelId
        if (channelId.equals(CHANNEL_PRODUCT)) {
            builder.setContentIntent(mainPendingIntent); // Ví dụ: mở MainActivity
        } else {
            builder.setContentIntent(fullPendingIntent); // Mở MainActivity và sau đó Discount hoặc Cart
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(getNotificationId(), builder.build());
    }

    private String determineChannelId(String title) {
        if (title.contains("Sản phẩm")) {
            return CHANNEL_PRODUCT;
        } else if (title.contains("Khuyến mãi")) {
            return CHANNEL_DISCOUNT;
        } else if (title.contains("Giỏ hàng")) {
            return CHANNEL_DELIVERY;
        }
        return CHANNEL_ID; // Mặc định trả về CHANNEL_ID mặc định nếu không khớp
    }

    private int getNotificationId() {
        return (int) System.currentTimeMillis(); // ID duy nhất dựa trên thời gian hiện tại
    }

}
