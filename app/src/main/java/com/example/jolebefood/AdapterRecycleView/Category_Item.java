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

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.Product;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Category_Item extends RecyclerView.Adapter<Category_Item.MyViewHolder>{
    View view;
    private List<CategoryDTO> mListFood;
    Context context;

    public Category_Item(Context context, List<CategoryDTO> mListFood) {
        this.context = context;
        this.mListFood = mListFood;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryDTO food = mListFood.get(position);
        if (food == null) {
            return;
        }
        holder.imgFood.setImageResource(food.getImage());
        holder.tvNameFood.setText(food.getTenDM());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(context, Product.class);
                context.startActivity(it1);
            }
        });

    }


    @Override
    public int getItemCount() {
        if (mListFood != null)
            return mListFood.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFood;
        private TextView tvNameFood;

        public MyViewHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvNameFood = itemView.findViewById(R.id.tv_name_food);


        }
    }

}
