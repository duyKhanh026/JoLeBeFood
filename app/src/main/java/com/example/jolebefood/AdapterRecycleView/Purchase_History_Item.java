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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DAO.CallFirebaseStrorage;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.Activity.OrderDetails;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Purchase_History_Item extends RecyclerView.Adapter<Purchase_History_Item.MyViewHolder>{

    private ArrayList<OrderDTO> dataList;

    CallFirebaseStrorage callFirebaseStrorage;

    Timestamp currrentTime = new Timestamp(System.currentTimeMillis());

    NumberFormat currencyFormat;



    public Purchase_History_Item(ArrayList<OrderDTO> dataList) {
        this.dataList = dataList;

        callFirebaseStrorage = new CallFirebaseStrorage();

        // Tạo một đối tượng NumberFormat với định dạng tiền tệ và quốc gia
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtID.setText(dataList.get(position).getMaDH());
        holder.txtTongTien.setText(currencyFormat.format(dataList.get(position).getTongTien()));
        holder.txtSoSanPham.setText(Integer.toString(dataList.get(position).getListOrderDetails().size()) + " sản phẩm");
        holder.txtSoLuong.setText("x" + Integer.toString(dataList.get(position).getListOrderDetails().get(0).getSL()));
        holder.txtGia.setText(currencyFormat.format(dataList.get(position).getListOrderDetails().get(0).getThanhTien()));
        holder.SetIMG(callFirebaseStrorage,dataList.get(position).getListOrderDetails().get(0).getMaMonAn());
        holder.txtTenMonAn.setText(dataList.get(position).getListOrderDetails().get(0).getTenMonAn());

        if (dataList.get(position).getThoiGianHoanThanh().after(currrentTime)){
            holder.txtTinhTrang.setText("Đơn hàng chưa hoàn tất");
        }else{
            holder.txtTinhTrang.setText("Đơn hàng được giao thành công");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy context từ view được bấm vào
                Context context = view.getContext();


                // Tạo Intent với context đã lấy
                Intent intent = new Intent(context, OrderDetails.class);

                intent.putExtra("ID",dataList.get(position).getMaDH());

                intent.putExtra("UID",dataList.get(position).getMaKH());

                context.startActivity(intent);


            }
        });

        holder.btnMuaLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDAO cartDAO = new CartDAO();
                for (OrderDetailsDTO orderDetails : dataList.get(position).getListOrderDetails()){
                    CartDTO cartDTO = new CartDTO(orderDetails.getMaMonAn(), orderDetails.getTenMonAn(), orderDetails.getSL(), "/"+orderDetails.getMaMonAn()+".jpg", orderDetails.getThanhTien());
                    cartDAO.SetData(view.getContext(),cartDTO, dataList.get(position).getMaKH());
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtID, txtTenMonAn, txtSoLuong, txtGia, txtSoSanPham, txtTongTien, txtTinhTrang;

        ImageView imgItem;

        Button btnMuaLai;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID_LS);
            imgItem = itemView.findViewById(R.id.imgMonAn_LS);
            txtTenMonAn = itemView.findViewById(R.id.txtTenMonAn_LS);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong_LS);
            txtGia = itemView.findViewById(R.id.txtGia_LS);
            txtSoSanPham = itemView.findViewById(R.id.txtSoSanPham_LS);
            txtTongTien = itemView.findViewById(R.id.txtTongTien_LS);
            txtTinhTrang = itemView.findViewById(R.id.txtTinhTrangDonHang);
            btnMuaLai = itemView.findViewById(R.id.btnMuaLai_LS);
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
