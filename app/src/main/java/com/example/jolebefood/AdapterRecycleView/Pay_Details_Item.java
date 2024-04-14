package com.example.jolebefood.AdapterRecycleView;

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
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class Pay_Details_Item extends RecyclerView.Adapter<Pay_Details_Item.MyViewHolder>{

    private ArrayList<CartDTO> dataList;

    // Lấy đơn vị tiền tệ dựa trên quốc gia (ở đây là Việt Nam)
    Currency currency = Currency.getInstance(new Locale("vi", "VN"));

    // Tạo một đối tượng NumberFormat với định dạng tiền tệ và quốc gia
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    CallFirebaseStrorage callFirebaseStrorage;

    public Pay_Details_Item(ArrayList<CartDTO> dataList) {
        this.dataList = dataList;
        // Đặt đơn vị tiền tệ cho đối tượng định dạng
        currencyFormat.setCurrency(currency);

        callFirebaseStrorage = new CallFirebaseStrorage();
    }

    @NonNull
    @Override
    public Pay_Details_Item.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_item, parent, false);
        return new Pay_Details_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pay_Details_Item.MyViewHolder holder, int position) {
        holder.txtTenMonAn.setText(dataList.get(position).getTenMonAn());
        holder.txtThanhTien.setText(currencyFormat.format(dataList.get(position).getTongTien()));
        holder.txtSoLuong.setText("x"+dataList.get(position).getSL());
        holder.SetIMG(callFirebaseStrorage, dataList.get(position).getMaMonAn());


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
            txtThanhTien = itemView.findViewById(R.id.txtGia_CTDH);
        }

        public void SetIMG(CallFirebaseStrorage callFirebaseStrorage, String id) {
            StorageReference mountainRef = callFirebaseStrorage.getStorageRef().child("/" + id + ".jpg");

            final long ONE_MEGABYTE = 1024 * 1024;
            mountainRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Convert bytes to Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    // Set the Bitmap to the ImageView
                    imgItem.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@androidx.annotation.NonNull Exception exception) {
                    Log.e("Kien Test ProductItem", "" + id + " " + exception.toString());
                }
            });
        }
    }
}
