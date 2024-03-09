package com.example.jolebefood;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.LSuMuaHang_Item;

import java.util.ArrayList;

public class LichSuMuaHang extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LSuMuaHang_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_su_mua_hang);


    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.RecycleView_LichSu);
    }


}