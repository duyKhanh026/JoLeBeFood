package com.example.jolebefood.AdapterRecycleView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DAO.CallFirebaseStrorage;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Product_Item extends RecyclerView.Adapter<Product_Item.MyViewHolder> {

    ArrayList<ProductDTO> dataList;

    CallFirebaseStrorage callFirebaseStrorage;

    public Product_Item(ArrayList<ProductDTO> dataList) {

        this.dataList = dataList;

        callFirebaseStrorage = new CallFirebaseStrorage();
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
        holder.quantity_sold.setText("Đã bán " + dataList.get(position).getSoluongdaban());
        holder.descripe.setText(dataList.get(position).getMoTa());
        holder.SetIMG(callFirebaseStrorage,dataList.get(position).getIMG());

        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), dataList.get(position).getTenMonAn(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price_product, descripe, quantity_sold;
        ImageView product_picture;
        ImageButton add_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_product = itemView.findViewById(R.id.name_product);
            price_product = itemView.findViewById(R.id.price_product);
            product_picture = itemView.findViewById(R.id.img_product);
            add_product = itemView.findViewById(R.id.add_productbtn);
            descripe = itemView.findViewById(R.id.describe_txt);
            quantity_sold = itemView.findViewById(R.id.sale_count);
        }

       public void bindData(ProductDTO dto1) {


        }

        public void SetIMG(CallFirebaseStrorage callFirebaseStrorage, String imgURL){
            StorageReference mountainRef = callFirebaseStrorage.getStorageRef().child(imgURL);

            final long ONE_MEGABYTE = 1024 * 1024;
            mountainRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Convert bytes to Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    // Set the Bitmap to the ImageView
                    product_picture.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@androidx.annotation.NonNull Exception exception) {
                    Log.e("Kien Test ProductItem", "" + imgURL + " "+ exception.toString());
                }
            });
        }


    }



}
