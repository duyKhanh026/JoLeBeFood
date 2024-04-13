package com.example.jolebefood;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jolebefood.SignIn_and_SignUp.Intro;
import com.example.jolebefood.fragment.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
//import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnCart, btnnoti;
    private static final int MY_PERMISSIONS_REQUEST_NOTIFICATION = 1;
    private static final String CHANNEL_ID = "my_firebase_channel";
    private Notification notification;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Lấy FragmentManager từ hỗ trợ Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Bắt đầu một FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        //String userId = "htdFaw5YgiRJtxCAkaP8jgSWeos2";

        HomeFragment fragment = new HomeFragment(userId);

        // Thêm một Fragment vào FragmentContainerView hoặc FrameLayout với một id duy nhất và thực thi ngay lập tức
        transaction.replace(R.id.fragment_container, fragment).commitNow();


//        FirebaseMessaging.getInstance().subscribeToTopic("News")
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msq = "Done";
//                        if (!task.isSuccessful()){
//                            msq = "Failed";
//                        }
//                    }
//                });

        btnCart = findViewById(R.id.btnMain_To_Cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cart.class);
                startActivity(intent);
            }
        });


        btnnoti = findViewById(R.id.btn_notifi);
        btnnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Đã nhấn", Toast.LENGTH_SHORT).show();
//                setLunchNotification();

               notification = new Notification(MainActivity.this);

                // Gọi phương thức showLunchNotification() để hiển thị thông báo ăn trưa
                notification.showLunchNotification();
            }

        });

        Notification_permission();
//
//        Notification notification = new Notification(MainActivity.this);
//        notification.showLunchNotification();       // Hiển thị thông báo lúc 11h hàng ngày
//        notification.showDinnerNotification();      // Hiển thị thông báo lúc 18h hàng ngày

        notification = new Notification(this);

        // Gọi phương thức để đặt thông báo cho 11h (nếu chưa được đặt)
        notification.scheduleLunchNotification();
        notification.scheduleDinnerNotification();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (!isFinishing()) {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc chắn muốn thoát không?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this,Intro.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
    }

    public void Notification_permission(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            // Quyền đã được cấp, có thể tạo thông báo
            Toast.makeText(this, "Quyền thông báo đã được cấp", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Chưa cấp quyền thông báo", Toast.LENGTH_SHORT).show();
            // Quyền chưa được cấp, yêu cầu người dùng cấp quyền
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    MY_PERMISSIONS_REQUEST_NOTIFICATION);
        }
    }


}