package com.example.jolebefood;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.AdapterRecycleView.Product_Item;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.ProductDAO.ProductDAO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.fragment.tab_home.CartFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Product_Item adapter;
    private ImageButton butcart;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product);

        // Lấy mã của người dùng

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        String categoryCode = getIntent().getStringExtra("category_code");
        String categoryName = getIntent().getStringExtra("category_name");

        Toast.makeText(this, categoryCode, Toast.LENGTH_SHORT).show();
        TextView title_product = findViewById(R.id.title_product);
        title_product.setText(categoryName);

        ImageButton butback = findViewById(R.id.button_back);
        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycleView_product);
        recyclerView.setLayoutManager(new LinearLayoutManager(Product.this));

        ArrayList<ProductDTO> dataList = new ArrayList<>();


        new ProductDAO().getList(dataList, list -> {
            ArrayList<ProductDTO> filteredList = new ArrayList<>();

            for (ProductDTO product : list) {
                if (product.getMaDanhMuc().equals(categoryCode)) {      // Check mã danh mục và thêm vào recycler
                    filteredList.add(product);
                }
            }

            adapter = new Product_Item(filteredList,userId);
            recyclerView.setAdapter(adapter);
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


}