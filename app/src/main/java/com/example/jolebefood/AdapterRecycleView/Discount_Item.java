package com.example.jolebefood.AdapterRecycleView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.ActivityForPay;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Discount_Item extends RecyclerView.Adapter<Discount_Item.MyViewHolder> {

    ArrayList<DiscountDTO> dataList;

    String UID = "";

    String PhuongThuc = "";

    Context context;

    public Discount_Item(ArrayList<DiscountDTO> dataList) {
        this.dataList = dataList;
    }

    public Discount_Item(Context context,ArrayList<DiscountDTO> dataList, String UID,String phuongThuc) {
        this.context = context;
        this.dataList = dataList;
        this.UID = UID;
        this.PhuongThuc = phuongThuc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.TenKM.setText(dataList.get(position).getTenkm());
        holder.pttt.setText(dataList.get(position).getPhuongthuctt());
        holder.img.setImageResource(R.drawable.momo_logo);

        if (dataList.get(position).getPhuongthuctt().equals("MOMO")){
            holder.img.setImageResource(R.drawable.momo_logo);
           }else if (dataList.get(position).getPhuongthuctt().equals("CASH")){
            holder.img.setImageResource(R.drawable.cash);
           }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UID.equals("")){
                    DiscountDTO discountDTO = dataList.get(position);
                    Intent intent = new Intent(view.getContext(), ActivityForPay.class);
                    intent.putExtra("UID",UID);
                    intent.putExtra("DiscountDTO",discountDTO);
                    intent.putExtra("PhuongThuc",PhuongThuc);
                    context.startActivity(intent);
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView TenKM;
        private TextView pttt;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TenKM = itemView.findViewById(R.id.discount_text);
            pttt = itemView.findViewById(R.id.method_payment);
            img = itemView.findViewById(R.id.imagemethod);

        }

    }



}
