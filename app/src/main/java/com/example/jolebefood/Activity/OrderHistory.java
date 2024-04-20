package com.example.jolebefood.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Purchase_History_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);

        AnhXa();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        recyclerView.setLayoutManager(new LinearLayoutManager(OrderHistory.this));

        ArrayList<OrderDTO> dataList = new ArrayList<>();


        //String userId = "htdFaw5YgiRJtxCAkaP8jgSWeos2";

        new OrderDAO().getList(userId,dataList, new OnGetListOrderListener() {
            @Override
            public void onGetListOrderSuccess() {
                adapter = new Purchase_History_Item(dataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onGetObjectSuccess() {

            }
        });







    }

    public void AnhXa(){

        recyclerView = findViewById(R.id.RecycleView_LichSu);
    }


}