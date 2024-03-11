package com.example.jolebefood.AdapterRecycleView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.ChiTietDonHang;
import com.example.jolebefood.R;

import java.util.ArrayList;

public class Category_Item extends RecyclerView.Adapter<Category_Item.MyViewHolder>{
    View view;
    private ArrayList<String> dataList;
    private ArrayList<Integer> imgIDList;

    public Category_Item(ArrayList<String> dataList, ArrayList<Integer> imgIDList) {
        this.dataList = dataList;
        this.imgIDList = imgIDList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(dataList.get(position), imgIDList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = view.findViewById(R.id.title_product);
                Toast.makeText(view.getContext(), "Danh mục " + tv.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_product);
            imageView = itemView.findViewById(R.id.img_product);
        }

        public void bindData(String data, int drw) {
            textView.setText(data);
            Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), drw);
            imageView.setImageBitmap(bitmap);
        }
    }

}
