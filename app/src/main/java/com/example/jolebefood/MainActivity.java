package com.example.jolebefood;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

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

}