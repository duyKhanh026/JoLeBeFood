package com.example.jolebefood.AdapterRecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Discount_Item extends RecyclerView.Adapter<Discount_Item.MyViewHolder> {

    ArrayList<String> dataList;

    public Discount_Item(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discount_item, parent, false);
        return new MyViewHolder(view);
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
        private TextView TenKM;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TenKM = itemView.findViewById(R.id.discount_text);

        }

       public void bindData(String data) {
           TenKM.setText(data);
        }


    }
}
