package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.MainActivity;
import com.example.jolebefood.R;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPageFragment extends Fragment {

    View view;

    Purchase_History_Item adapter;

    MainActivity mainActivity;

    String uid;

    public MyPageFragment(String uid) {
        this.uid = uid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.activity_order_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecycleView_LichSu);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<OrderDTO> dataList = new ArrayList<>();

        new OrderDAO().getList(uid, dataList, new OnGetListOrderListener() {
            @Override
            public void onGetListOrderSuccess() {
                adapter = new Purchase_History_Item(dataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onGetObjectSuccess() {

            }
        });

        return view;
    }
}