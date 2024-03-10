package com.example.jolebefood.AdapterRecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.R;

import java.util.ArrayList;

public class ChiTietDonHang_Item extends RecyclerView.Adapter<ChiTietDonHang_Item.MyViewHolder>{

    private ArrayList<String> dataList;

    public ChiTietDonHang_Item(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ChiTietDonHang_Item.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chitietdonhang_item, parent, false);
        return new ChiTietDonHang_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtTenMonAn_CTDH);
        }

        public void bindData(String data) {
            textView.setText(data);
        }
    }


}
