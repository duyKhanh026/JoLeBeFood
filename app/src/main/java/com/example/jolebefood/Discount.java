package com.example.jolebefood;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DTO.DiscountDTO;

import java.util.ArrayList;
import java.util.List;

public class Discount extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Discount_Item adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.discount_activity);

        recyclerView = findViewById(R.id.RecycleView_KhuyenMai);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<DiscountDTO> dataList = new ArrayList<>();

        new DiscountDAO().getList(dataList, new OnGetListDiscountListener() {
            @Override
            public void onGetListDiscountSuccess(List<DiscountDTO> list) {
                adapter = new Discount_Item(dataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onGetObjectSuccess() {

            }
        });

    }


}
