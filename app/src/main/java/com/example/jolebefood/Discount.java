package com.example.jolebefood;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class Discount extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Discount_Item adapter;

    private String UID,PhuongThuc;
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
                    adapter = new Discount_Item(filteredList);
                }
                else{
                    Log.e("Kien tee",PhuongThuc);
                    if (!PhuongThuc.equals("Chọn phương thức thanh toán")){
                        adapter = new Discount_Item(Discount.this,getListTheoPhuongThuc(filteredList,PhuongThuc),UID,PhuongThuc);
                    }
                    else {
                        adapter = new Discount_Item(Discount.this,filteredList,UID,PhuongThuc);
                    }
                }

                recyclerView.setAdapter(adapter);
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
