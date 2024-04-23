package com.example.jolebefood.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jolebefood.R;
import com.example.jolebefood.SignIn_and_SignUp.Intro;
import com.example.jolebefood.fragment.HomeFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private ImageButton btnCart, btnnoti;
    private static final int MY_PERMISSIONS_REQUEST_NOTIFICATION = 1;

    private static final int REQUEST_CODE_PERMISSIONS_GPS = 101; // Choose a unique code

    private static final String CHANNEL_ID = "my_firebase_channel";
    public Notification notification;
    public Notification notification1;

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

        btnCart = findViewById(R.id.btnMain_To_Cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Cart.class);
                startActivity(intent);
            }
        });


//        btnnoti = findViewById(R.id.btn_notifi);
//        btnnoti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Đã nhấn", Toast.LENGTH_SHORT).show();
////                setLunchNotification();
//
//               notification = new Notification(MainActivity.this);
//
//                // Gọi phương thức showLunchNotification() để hiển thị thông báo ăn trưa
//                notification.scheduleLunchNotification();
//            }
//
//        });

        GPS_permission();

        Notification_permission();

//        notification = new Notification(this);
//        notification1 = new Notification(this);
//
//        notification1.scheduleLunchNotification();       // 11h
//        notification.scheduleDinnerNotification();      // 18h

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
            Log.e("Kien Main","Đã được quyền thông báo cấp");

        } else {
            Toast.makeText(this, "Chưa cấp quyền thông báo", Toast.LENGTH_SHORT).show();
            // Quyền chưa được cấp, yêu cầu người dùng cấp quyền
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                    MY_PERMISSIONS_REQUEST_NOTIFICATION);
        }
    }

    public void GPS_permission(){
        // Kiểm tra quyền truy cập vị trí hiện tại
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSIONS_GPS);
        }
        else{
            Log.e("Kien Main","Đã được quyền gps cấp");
        }
    }


}