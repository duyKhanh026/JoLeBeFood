package com.example.jolebefood.AdapterRecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DTO.KhuyenMaiDTO;
import com.example.jolebefood.R;

import java.util.ArrayList;

public class KhuyenMai_Item extends RecyclerView.Adapter<KhuyenMai_Item.MyViewHolder> {

    ArrayList<KhuyenMaiDTO> dataList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.khuyenmai_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        KhuyenMaiDTO khuyenMaiDTO = dataList.get(position);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView TenKM;
        private TextView phuongthuc;
        private ImageView img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TenKM = itemView.findViewById(R.id.discount_text);
            phuongthuc = itemView.findViewById(R.id.method_payment);
            img = itemView.findViewById(R.id.imagemethod);
        }

       public void bindData(String data) {
//            textView.setText(data);
        }


    }
}
