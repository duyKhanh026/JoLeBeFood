package com.example.jolebefood.AdapterRecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Order_Details_Item extends RecyclerView.Adapter<Order_Details_Item.MyViewHolder>{

    private List<OrderDetailsDTO> dataList;

    public Order_Details_Item(List<OrderDetailsDTO> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Order_Details_Item.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_item, parent, false);
        return new Order_Details_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTenMonAn.setText(dataList.get(position).getTenMonAn());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTenMonAn, txtSoLuong, txtThanhTien;

        ImageView imgItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonAn = itemView.findViewById(R.id.txtTenMonAn_CTDH);
            imgItem = itemView.findViewById(R.id.imgMonAn_CTDH);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong_CTDH);
            txtThanhTien = itemView.findViewById(R.id.txtThanhTien_CTHD);
        }

    }


}
