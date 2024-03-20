package com.example.jolebefood.AdapterRecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.OrderDetails;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class Purchase_History_Item extends RecyclerView.Adapter<Purchase_History_Item.MyViewHolder>{

    private List<OrderDTO> dataList;

    public Purchase_History_Item(List<OrderDTO> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.bindData(Integer.toString(dataList.get(position).getTongTien()));
        holder.txtID.setText(dataList.get(position).getMaDH());
        holder.txtTongTien.setText(Integer.toString(dataList.get(position).getTongTien()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy context từ view được bấm vào
                Context context = view.getContext();

                Toast.makeText(context, holder.txtID.getText(), Toast.LENGTH_SHORT).show();
                // Tạo Intent với context đã lấy
                Intent intent = new Intent(context, OrderDetails.class);
                // Start activity bằng context
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtID,txtTenMonAn,txtSoLuong,txtGia,txtSoSanPham, txtTongTien, txtTinhTrang;

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

    }

}
