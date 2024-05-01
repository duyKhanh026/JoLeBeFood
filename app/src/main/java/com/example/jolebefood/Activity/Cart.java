package com.example.jolebefood.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Cart_Item;
import com.example.jolebefood.AsyncTask.AsyncTask_Cart;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button payBtn, orderBtn;
    private TextView total_pay, emptyTxt;
    private ImageButton back_button;
    private ProgressBar progressBar;
    private ImageView emptyCartImageView;
    NumberFormat currencyFormat;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        recyclerView = findViewById(R.id.cardView);
        back_button = findViewById(R.id.button_back);
        payBtn = findViewById(R.id.payBtn);
        total_pay = findViewById(R.id.total_pay);
        progressBar = findViewById(R.id.progressBar_Cart);
        emptyTxt = findViewById(R.id.emptyTxt);
        ConstraintLayout layout = findViewById(R.id.layout);
        orderBtn = findViewById(R.id.orderBtn);
        emptyCartImageView = findViewById(R.id.emptyCartImageView);

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
                new AsyncTask_Cart(recyclerView, progressBar, Cart.this, datalist).execute();

                int totalAmount = calculateTotalAmount(datalist);
                total_pay.setText(currencyFormat.format(totalAmount));
            }
            @Override
            public void onGetListCartEmpty() {
                emptyTxt.setVisibility(View.VISIBLE);
                orderBtn.setVisibility(View.VISIBLE);
                emptyCartImageView.setVisibility((View.VISIBLE));
                recyclerView.setVisibility(View.GONE);
                layout.setVisibility(View.GONE);
            }

        });


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this,ActivityForPay.class);
                intent.putExtra("UID",userId);
                startActivity(intent);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private int calculateTotalAmount(ArrayList<CartDTO> cartItems) {
        Cart_Item adapter = new Cart_Item(cartItems, total_pay);
        recyclerView.setAdapter(adapter);
        int totalAmount = 0;
        for (CartDTO item : cartItems) {
            totalAmount += item.getTongTien();
        }
        return totalAmount;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (!isFinishing()) {
            Intent intent = new Intent(Cart.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
