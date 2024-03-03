package com.example.jolebefood;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jolebefood.fragment.HomeFragment;

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

        // Thêm một Fragment vào FragmentContainerView hoặc FrameLayout với một id duy nhất và thực thi ngay lập tức
        transaction.replace(R.id.fragment_container, new HomeFragment()).commitNow();
    }
}