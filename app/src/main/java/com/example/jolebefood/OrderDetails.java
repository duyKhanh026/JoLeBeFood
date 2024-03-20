package com.example.jolebefood;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView txtName,txtPhone,txtDiaChi,txtTongTien,txtGiamGia,txtThanhTien,txtPhuongThuc,txtThoiGianDat,txtThoiGianHT;

    private Button btnThanhToan;

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
        txtName = findViewById(R.id.txtName_CT);
        txtPhone = findViewById(R.id.txtPhone_CT);
        txtDiaChi = findViewById(R.id.txtDiaChi_CT);
        txtTongTien = findViewById(R.id.txtTongTien_CTHD);
        txtGiamGia = findViewById(R.id.txtGiamGia_CTHD);
        txtThanhTien = findViewById(R.id.txtThanhTien_CTHD);
        txtPhuongThuc = findViewById(R.id.txtPhuongThuc_CTHD);
        txtThoiGianDat = findViewById(R.id.txtThoiGianDat_CTHD);
        txtThoiGianHT = findViewById(R.id.txtThoiGianHT_CTHD);
        btnThanhToan = findViewById(R.id.btnThanhToan_CTHD);
    }
}