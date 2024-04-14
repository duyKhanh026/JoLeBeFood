package com.example.jolebefood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.AsyncTask.AsyncTask_Discount;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class Discount extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private String UID,PhuongThuc;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.discount_activity);

        ImageButton butback = findViewById(R.id.button_back);
        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Lấy mã người dùng từ giao diện ActivityForPay gửi tới
        Intent intent = getIntent();

        UID = intent.getStringExtra("UID");

        PhuongThuc = intent.getStringExtra("PhuongThuc");


        recyclerView = findViewById(R.id.RecycleView_KhuyenMai);

        progressBar = findViewById(R.id.progressBar_Discount);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<DiscountDTO> dataList = new ArrayList<>();

        new DiscountDAO().getList(dataList, new OnGetListDiscountListener() {
            ArrayList<DiscountDTO> filteredList = new ArrayList<>();
            @Override
            public void onGetListDiscountSuccess(List<DiscountDTO> list) {

                for (DiscountDTO discountDTO : list){
                    if (discountDTO.getSoluong() != 0){
                        filteredList.add(discountDTO);
                    }
                }

                if (UID == null){
                    new AsyncTask_Discount(recyclerView,progressBar,Discount.this,filteredList).execute();
                }
                else{
                    if (!PhuongThuc.equals("Chọn phương thức thanh toán")){
                        new AsyncTask_Discount(UID,PhuongThuc,recyclerView,progressBar,Discount.this,getListTheoPhuongThuc(filteredList,PhuongThuc)).execute();
                    }
                    else {
                        new AsyncTask_Discount(UID,PhuongThuc,recyclerView,progressBar,Discount.this,filteredList).execute();
                    }
                }

            }

            @Override
            public void onGetObjectSuccess() {

            }
        });

    }

    public ArrayList<DiscountDTO> getListTheoPhuongThuc(ArrayList<DiscountDTO> dataList, String PhuongThuc){
        ArrayList<DiscountDTO> list = new ArrayList<>();
        for (DiscountDTO s : dataList){
            if (s.getPhuongthuctt().equals(PhuongThuc)){
                list.add(s);
            }
        }
        return list;
    }


}
