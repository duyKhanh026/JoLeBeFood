package com.example.jolebefood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Product_Item;
import com.example.jolebefood.AsyncTask.AsyncTask_Product;
import com.example.jolebefood.DAO.ProductDAO.ProductDAO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Product extends AppCompatActivity {
    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Product_Item adapter;
    private ImageButton butcart;

    private String userId;
    private String categoryCode = "DM001";
    private String categoryName = "Sản phẩm";

    private ArrayList<ProductDTO> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product);

        // Lấy mã của người dùng

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        String categoryCode = getIntent().getStringExtra("category_code");
        String categoryName = getIntent().getStringExtra("category_name");

        TextView title_product = findViewById(R.id.title_product);
        title_product.setText(categoryName);

        ImageButton butback = findViewById(R.id.button_back);
        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBar = findViewById(R.id.progressBar_product);

        recyclerView = findViewById(R.id.recycleView_product);

        recyclerView.setLayoutManager(new LinearLayoutManager(Product.this));


        dataList = new ArrayList<>();

        new ProductDAO().getList(dataList, list -> {
            ArrayList<ProductDTO> filteredList = new ArrayList<>();

            for (ProductDTO product : list) {
                if (product.getMaDanhMuc().equals(categoryCode)) {      // Check mã danh mục và thêm vào recycler
                    filteredList.add(product);
                }
            }

            new AsyncTask_Product(userId,recyclerView,progressBar,Product.this,filteredList).execute();
        });

        butcart = findViewById(R.id.button_cart);
        butcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Product.this,Cart.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}