package com.example.jolebefood;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.KhuyenMai_Item;
import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.DTO.KhuyenMaiDTO;

import java.util.ArrayList;

public class KhuyenMai extends AppCompatActivity {

    private RecyclerView recyclerView;

    private KhuyenMai_Item adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.khuyenmai);

    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.RecycleView_KhuyenMai);
    }

//    public void createkm(){
//        kmai.add(new KhuyenMaiDTO("KM001", "Giảm 10", 10, "momo", true));
//        kmai.add(new KhuyenMaiDTO("KM002", "Giảm 20", 20, "momo", true));
//        kmai.add(new KhuyenMaiDTO("KM003", "Giảm 30", 30, "momo", true));
//        kmai.add(new KhuyenMaiDTO("KM004", "Giảm 40", 40, "momo", true));
//    }
}
