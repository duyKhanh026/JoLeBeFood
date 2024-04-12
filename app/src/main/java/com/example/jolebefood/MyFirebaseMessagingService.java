package com.example.jolebefood;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
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
    private static final String CHANNEL_PRODUCT = "product_channel";
    private static final String CHANNEL_DISCOUNT = "discount_channel";
    private static final String CHANNEL_DELIVERY = "delivery_channel";

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

        // Xử lý tin nhắn dữ liệu (nếu có)
        if (remoteMessage.getData().size() > 0) {

        } else {
            // Xử lý tin nhắn thông báo
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            String channelId = determineChannelId(title);

            Intent mainIntent  = new Intent(this, MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);

//            Intent productIntent = new Intent(this, MainActivity.class);

            Intent discountIntent = new Intent(this, Discount.class);

            Intent deliveryIntent = new Intent(this, Cart.class);

            Intent secondaryIntent = new Intent(this, Cart.class);


            PendingIntent secondaryPendingIntent = PendingIntent.getActivity(this, 0, secondaryIntent, 0);

            PendingIntent discountPendingIntent = PendingIntent.getActivity(this, 0, discountIntent,0);

            PendingIntent deliveryPendingIntent = PendingIntent.getActivity(this, 0, deliveryIntent,0);


            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(mainIntent);      // thêm activity 1

//            if (channelId.equals("Khuyến mãi")){
//                stackBuilder.addNextIntent(discountIntent);
//            } else if (channelId.equals("Giao hàng")) {
//                stackBuilder.addNextIntent(deliveryIntent);
//            }

            stackBuilder.addNextIntent(deliveryIntent);         // thêm activity 2     // thay dòng này bằng 4 dòng if else ở trên thì
                                                                                        //nó ko chạy được

            PendingIntent fullPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

//            PendingIntent productPendingIntent = PendingIntent.getActivity(this, 0, productIntent, PendingIntent.FLAG_UPDATE_CURRENT);





            // Tạo và hiển thị thông báo
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.icon_km)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setContentIntent(fullPendingIntent)
                    .setAutoCancel(true);

            if (channelId.equals(CHANNEL_PRODUCT)) {
//                builder.setContentIntent(productPendingIntent);
            } else if (channelId.equals(CHANNEL_DISCOUNT)) {
//                builder.setContentIntent(discountPendingIntent);
            } else if (channelId.equals(CHANNEL_DELIVERY)) {
//                builder.setContentIntent(deliveryPendingIntent);
            }

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(getNotificationId(), builder.build());
        }
    }

    private String determineChannelId(String title) {
        // Ví dụ: dựa vào tiêu đề để xác định loại thông báo
        if (title.contains("Sản phẩm")) {
            return CHANNEL_PRODUCT;
        } else if (title.contains("Khuyến mãi")) {
            return CHANNEL_DISCOUNT;
        } else if (title.contains("Giao hàng")) {
            return CHANNEL_DELIVERY;
        }
        // Mặc định trả về CHANNEL_ID mặc định nếu không khớp loại nào
        return CHANNEL_ID;
    }

    // Phương thức để lấy ID định danh cho thông báo
    private int getNotificationId() {
        // Mã ID có thể được tạo một cách duy nhất dựa trên thời gian, hoặc sử dụng ID duy nhất từ server
        return (int) System.currentTimeMillis(); // Ví dụ đơn giản sử dụng thời gian hiện tại làm ID
    }
}
