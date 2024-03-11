package com.example.jolebefood;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Order_Details_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        AnhXa();

        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetails.this));

        ArrayList<String> dataList = new ArrayList<>();

        dataList.add("Món gà");
        dataList.add("Món bò");
        dataList.add("Món heo");
        dataList.add("Món rau");


        adapter = new Order_Details_Item(dataList);
        recyclerView.setAdapter(adapter);
    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.listSP_CTDH);
    }
}