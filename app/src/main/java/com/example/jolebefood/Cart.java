package com.example.jolebefood;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Cart_Item;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DTO.CartDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    private RecyclerView recyclerView;

    private Cart_Item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        recyclerView = findViewById(R.id.cardView);

        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));

        ArrayList<CartDTO> datalist = new ArrayList<>();

        new CartDAO().getList(userId, datalist, new OnGetListCartListener() {
            @Override
            public void onGetListCartSuccess() {
                adapter = new Cart_Item(datalist);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onGetObjectSuccess() {

            }
        });
    }

}
