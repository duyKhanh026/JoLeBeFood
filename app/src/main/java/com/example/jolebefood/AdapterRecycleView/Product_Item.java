package com.example.jolebefood.AdapterRecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Product_Item extends RecyclerView.Adapter<Product_Item.MyViewHolder> {

    ArrayList<ProductDTO> dataList;

    public Product_Item(ArrayList<ProductDTO> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bindData(dataList.get(position));
        holder.name_product.setText(dataList.get(position).getTenMonAn());
        int gia = dataList.get(position).getGia();
        holder.price_product.setText(String.valueOf(gia));
        holder.product_picture.setImageResource(R.drawable.com_ga_xoi_mo);
        holder.descripe.setText(dataList.get(position).getMoTa());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price_product, descripe;
        ImageView product_picture;
        ImageButton add_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_product = itemView.findViewById(R.id.name_product);
            price_product = itemView.findViewById(R.id.price_product);
            product_picture = itemView.findViewById(R.id.img_product);
            add_product = itemView.findViewById(R.id.add_productbtn);
            descripe = itemView.findViewById(R.id.describe_txt);
        }

       public void bindData(ProductDTO dto1) {


        }


    }



}
