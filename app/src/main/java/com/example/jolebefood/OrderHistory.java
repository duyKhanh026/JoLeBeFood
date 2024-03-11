package com.example.jolebefood;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;

public class OrderHistory extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Purchase_History_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);


    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.RecycleView_LichSu);
    }


}