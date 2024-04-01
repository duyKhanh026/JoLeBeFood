package com.example.jolebefood.fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jolebefood.AdapterRecycleView.Cart_Item;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    EditText discountTxt;
    Button discountBtn, payBtn;
    TextView total, delivery, total_pay;
    Cart_Item adapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  =  inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cardView);
        discountTxt = view.findViewById(R.id.discountTxt);
        discountBtn = view.findViewById(R.id.discountBtn);
        payBtn = view.findViewById(R.id.payBtn);
        total = view.findViewById(R.id.total);
        delivery = view.findViewById(R.id.delivery);
        total_pay = view.findViewById(R.id.total_pay);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList<CartDTO> datalist = new ArrayList<>();

//        new CartDAO().getList(datalist, list -> {
//            adapter = new Cart_Item(datalist);
//            recyclerView.setAdapter(adapter);
//        });


        return view;
    }

}