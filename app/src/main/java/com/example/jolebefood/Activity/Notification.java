package com.example.jolebefood.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class Notification {

    public static final String CHANNEL_ID = "my_firebase_channel";
    private static final int NOTIFICATION_ID_LUNCH = 1;

    private Context context;

    public Notification(Context context) {
        this.context = context;
    }

    public void showLunchNotification() {
        // Tạo intent để gửi tới LunchReceiver khi thông báo được kích hoạt
        Intent lunchIntent = new Intent(context, LunchReceiver.class);
        lunchIntent.setAction("LUNCH_NOTIFICATION");
        lunchIntent.putExtra("notification_title", "Đã đến giờ ăn trưa 9h");
        lunchIntent.putExtra("notification_content", "Bạn hãy chuẩn bị cho bữa ăn trưa nhé!");

        // Tạo PendingIntent để gửi broadcast khi thông báo được kích hoạt
        PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(
                context,
                0, // Đổi thành một requestCode duy nhất, ví dụ: 100
                lunchIntent,
                PendingIntent.FLAG_UPDATE_CURRENT // Sử dụng FLAG_UPDATE_CURRENT thay vì FLAG_IMMUTABLE
        );
        // Lấy thời điểm hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9); // Đặt giờ là 8
        calendar.set(Calendar.MINUTE, 20);     // Đặt phút là 38
        calendar.set(Calendar.SECOND, 0);       // Đặt giây là 0

        // Đặt thông báo cho thời điểm đã chỉ định
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            // Sử dụng RTC_WAKEUP để đảm bảo thiết bị bật lên khi đến thời điểm thông báo
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), lunchPendingIntent);
        }
    }

    public void showDinnerNotification() {
        // Tạo intent để gửi tới LunchReceiver khi thông báo được kích hoạt
        Intent lunchIntent = new Intent(context, LunchReceiver.class);
        lunchIntent.setAction("LUNCH_NOTIFICATION");
        lunchIntent.putExtra("notification_title", "Đã đến giờ ăn chiều");
        lunchIntent.putExtra("notification_content", "Bạn hãy chuẩn bị cho bữa ăn chiều nhé!");

        // Tạo PendingIntent để gửi broadcast khi thông báo được kích hoạt
        PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(context, 0, lunchIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Lấy thời điểm hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18); // Đặt giờ là 8
        calendar.set(Calendar.MINUTE, 00);     // Đặt phút là 38
        calendar.set(Calendar.SECOND, 0);       // Đặt giây là 0

        // Đặt thông báo cho thời điểm đã chỉ định
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            // Sử dụng RTC_WAKEUP để đảm bảo thiết bị bật lên khi đến thời điểm thông báo
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), lunchPendingIntent);
        }
    }

    public void scheduleLunchNotification() {
        AlarmManager alarmManager1 = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent lunchIntent = new Intent(context, LunchReceiver.class);
        lunchIntent.setAction("LUNCH_NOTIFICATION");
        lunchIntent.putExtra("notification_title", "Thông báo 11h");
        lunchIntent.putExtra("notification_content", "Bạn hãy chuẩn bị cho bữa trưa nhé!");

        PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                lunchIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Thêm FLAG_IMMUTABLE
        );
        // Đặt thông báo cho 11h
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);

        if (alarmManager1 != null) {
            alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
        }
    }

    public void scheduleDinnerNotification() {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent lunchIntent = new Intent(context, LunchReceiver.class);
        lunchIntent.setAction("LUNCH_NOTIFICATION");
        lunchIntent.putExtra("notification_title", "Thông báo 18h");
        lunchIntent.putExtra("notification_content", "Bạn hãy chuẩn bị cho bữa tối nhé!");

        PendingIntent lunchPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                lunchIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE // Thêm FLAG_IMMUTABLE
        );
        // Đặt thông báo cho 11h
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, lunchPendingIntent);
        }
    }
}

