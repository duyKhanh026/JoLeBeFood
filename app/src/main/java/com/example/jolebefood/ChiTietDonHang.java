package com.example.jolebefood;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.ChiTietDonHang_Item;

import java.util.ArrayList;

public class ChiTietDonHang extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ChiTietDonHang_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

        AnhXa();

        recyclerView.setLayoutManager(new LinearLayoutManager(ChiTietDonHang.this));

        ArrayList<String> dataList = new ArrayList<>();

        dataList.add("Món gà");
        dataList.add("Món bò");
        dataList.add("Món heo");
        dataList.add("Món rau");


        adapter = new ChiTietDonHang_Item(dataList);
        recyclerView.setAdapter(adapter);
    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.listSP_CTDH);
    }
}