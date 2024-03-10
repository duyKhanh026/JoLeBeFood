package com.example.jolebefood.AdapterRecycleView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.R;

import java.util.ArrayList;

public class KhuyenMai_Item extends RecyclerView.Adapter<KhuyenMai_Item.MyViewHolder> {

    ArrayList<String> listkm;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtDanhMuc_LS);
        }

       public void bindData(String data) {
            textView.setText(data);
        }
    }
}
