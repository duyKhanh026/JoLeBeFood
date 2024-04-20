package com.example.jolebefood.AdapterRecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DAO.CallFirebaseStrorage;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.Activity.Product;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Category_Item extends RecyclerView.Adapter<Category_Item.MyViewHolder>{
    View view;
    private ArrayList<CategoryDTO> mListFood;
    Context context;
    CallFirebaseStrorage callFirebaseStrorage;

    public Category_Item(Context context, ArrayList<CategoryDTO> mListFood) {
        this.context = context;
        this.mListFood = mListFood;

        callFirebaseStrorage = new CallFirebaseStrorage();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bindData(mListFood.get(position));
//        holder.imgFood.setImageResource(mListFood.get(position).getImage());
        holder.tvNameFood.setText(mListFood.get(position).getTenDM());

        holder.SetIMG(callFirebaseStrorage,mListFood.get(position).getIMG());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(context, Product.class);
                it1.putExtra("category_code", mListFood.get(position).getMaDM());
                it1.putExtra("category_name", mListFood.get(position).getTenDM());
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
        public void bindData(CategoryDTO dto1) {
        }
        public void SetIMG(CallFirebaseStrorage callFirebaseStrorage, String imgURL) {
            StorageReference mountainRef = callFirebaseStrorage.getStorageRef().child(imgURL);

            final long ONE_MEGABYTE = 1024 * 1024;
            mountainRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Convert bytes to Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    // Set the Bitmap to the ImageView
                    imgFood.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@androidx.annotation.NonNull Exception exception) {
                    Log.e("Kien Test ProductItem", "" + imgURL + " " + exception.toString());
                }
            });
        }
    }

}
