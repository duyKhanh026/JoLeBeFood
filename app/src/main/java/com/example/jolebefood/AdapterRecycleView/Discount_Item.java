package com.example.jolebefood.AdapterRecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Discount_Item extends RecyclerView.Adapter<Discount_Item.MyViewHolder> {

    ArrayList<DiscountDTO> dataList;

    public Discount_Item(ArrayList<DiscountDTO> dataList) {
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
        private TextView pttt;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TenKM = itemView.findViewById(R.id.discount_text);
            pttt = itemView.findViewById(R.id.method_payment);
            img = itemView.findViewById(R.id.imagemethod);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    // Lấy giá trị khuyến mãi từ DiscountDTO
                    DiscountDTO discount = dataList.get(position);
                    String maKM = discount.getMakm();
                    int giaTriKM = discount.getGiatrikm();
                    String pttt = discount.getPhuongthuctt();
                    Toast.makeText(itemView.getContext(), "Mã: " + maKM +", Giá trị " +giaTriKM + ", PTTT: " + pttt, Toast.LENGTH_SHORT).show();
                }
            });
        }

       public void bindData(DiscountDTO dto1) {

           TenKM.setText(dto1.getTenkm());
           pttt.setText("Use with " + dto1.getPhuongthuctt());
           if (dto1.getPhuongthuctt().equals("MOMO")){
               img.setImageResource(R.drawable.momo_logo);
           }else if (dto1.getPhuongthuctt().equals("CASH")){
               img.setImageResource(R.drawable.cash);
           }

        }


    }



}
