package com.example.jolebefood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jolebefood.fragment.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String userId = user.getUid();

        String userId = "htdFaw5YgiRJtxCAkaP8jgSWeos2";

        HomeFragment fragment = new HomeFragment(userId);

        // Thêm một Fragment vào FragmentContainerView hoặc FrameLayout với một id duy nhất và thực thi ngay lập tức
        transaction.replace(R.id.fragment_container, fragment).commitNow();
    }

}