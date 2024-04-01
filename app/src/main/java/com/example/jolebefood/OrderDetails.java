package com.example.jolebefood;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.DTO.OrderDTO;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView txtName,txtPhone,txtDiaChi,txtTongTien,txtGiamGia,txtThanhTien,txtPhuongThuc,txtThoiGianDat,txtThoiGianHT;

    private Button btnThanhToan;

    private Order_Details_Item adapter;

    private OrderDTO orderDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        AnhXa();




        // Nhận Intent mà đã gửi từ Activity trước
        Intent intent = getIntent();

        // Trích xuất Bundle từ Intent
        Bundle receivedBundle = intent.getBundleExtra("Data");

        // Kiểm tra xem Bundle có tồn tại hay không
        if (receivedBundle != null) {
            // Trích xuất đối tượng từ Bundle
            orderDTO = (OrderDTO) receivedBundle.getSerializable("Object");

            // Trích xuất chuỗi từ Bundle
            String type = receivedBundle.getString("Type");


            if (orderDTO == null) {
                Log.e("Kien test details","Object null");

            }
            if (type != null) {
                Log.e("Kien test details","String null");
            }
        }
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

    public void SetData(OrderDTO orderDTO){
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetails.this));
        adapter = new Order_Details_Item(orderDTO.getListOrderDetails());
        recyclerView.setAdapter(adapter);
    }
}