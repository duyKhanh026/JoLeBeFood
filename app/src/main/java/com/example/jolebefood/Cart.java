package com.example.jolebefood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private Button payBtn;
    private TextView total, delivery, total_pay;
    private Cart_Item adapter;
    private ImageButton back_button;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        recyclerView = findViewById(R.id.cardView);
        back_button = findViewById(R.id.button_back);
        payBtn = findViewById(R.id.payBtn);
        total = findViewById(R.id.total);
        delivery = findViewById(R.id.delivery);
        total_pay = findViewById(R.id.total_pay);

        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));

        ArrayList<CartDTO> datalist = new ArrayList<>();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        new CartDAO().getList(userId, datalist, new OnGetListCartListener() {
            @Override
            public void onGetListCartSuccess() {
                adapter = new Cart_Item(datalist);
                recyclerView.setAdapter(adapter);

                int totalAmount = calculateTotalAmount(datalist);
                total.setText(String.valueOf(totalAmount));

                int deliveryFee = 10000;
                delivery.setText(String.valueOf(deliveryFee));

                int totalPayAmount = totalAmount + deliveryFee;
                total_pay.setText(String.valueOf(totalPayAmount));
            }
            @Override
            public void onGetObjectSuccess() {
            }
        });

    }

    private int calculateTotalAmount(ArrayList<CartDTO> cartItems) {
        int totalAmount = 0;
        for (CartDTO item : cartItems) {
            totalAmount += item.getTongTien();
        }
        return totalAmount;
    }

}
