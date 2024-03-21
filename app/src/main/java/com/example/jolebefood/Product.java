package com.example.jolebefood;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.AdapterRecycleView.Product_Item;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.ProductDAO.ProductDAO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.ArrayList;

public class Product extends AppCompatActivity {
    private RecyclerView recyclerView;

    private Product_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product);

        TextView title_product = findViewById(R.id.title_product);
        recyclerView = findViewById(R.id.recycleView_product);


        recyclerView.setLayoutManager(new LinearLayoutManager(Product.this));
//
        ArrayList<ProductDTO> dataList = new ArrayList<>();

        dataList.add(new ProductDTO("SP001", "CƠM GÀ XỐI MỠ", "CƠM THƠM NGON", 5, 50000, "DM001",30));
        dataList.add(new ProductDTO("SP001", "CƠM GÀ XỐI MỠ", "CƠM THƠM NGON", 5, 100000, "DM001",25));
        dataList.add(new ProductDTO("SP001", "CƠM GÀ XỐI MỠ", "CƠM THƠM NGON", 5, 70000, "DM001",22));
        dataList.add(new ProductDTO("SP001", "CƠM GÀ XỐI MỠ", "CƠM THƠM NGON", 5, 30000, "DM001",45));
        dataList.add(new ProductDTO("SP001", "CƠM GÀ XỐI MỠ", "CƠM THƠM NGON", 5, 40000, "DM001",13));
        dataList.add(new ProductDTO("SP001", "CƠM GÀ XỐI MỠ", "CƠM THƠM NGON", 5, 60000, "DM001",5));

        new ProductDAO().getList(dataList, list -> {
            adapter = new Product_Item(dataList);
            recyclerView.setAdapter(adapter);
        });

//        adapter = new Product_Item(dataList);
//        recyclerView.setAdapter(adapter);
    }
}
