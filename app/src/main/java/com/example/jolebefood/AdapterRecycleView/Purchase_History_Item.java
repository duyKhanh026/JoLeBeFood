package com.example.jolebefood.AdapterRecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DAO.CategoryDAO.CategoryDAO;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.OrderDetails;
import com.example.jolebefood.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Purchase_History_Item extends RecyclerView.Adapter<Purchase_History_Item.MyViewHolder>{

    private List<CategoryDTO> dataList;

    public Purchase_History_Item(List<CategoryDTO> dataList) {
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
        holder.bindData(dataList.get(position).getTenDM());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy context từ view được bấm vào
                Context context = view.getContext();
//
//                Toast.makeText(context, holder.textView.getText(), Toast.LENGTH_SHORT).show();
//                // Tạo Intent với context đã lấy
//                Intent intent = new Intent(context, OrderDetails.class);
//                // Start activity bằng context
//                context.startActivity(intent);

                new CategoryDAO().AddCategory(new CategoryDTO("4","Cá heo"),context);


            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtTenMonAn_LS);
        }

        public void bindData(String data) {
            textView.setText(data);
        }
    }

}
