package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AsyncTask.AsyncTask_History;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.R;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    View view;


    String uid;

    public MyPageFragment(String uid) {
        this.uid = uid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.activity_order_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecycleView_LichSu);

        ProgressBar progressBar = view.findViewById(R.id.progressBar_LichSu);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<OrderDTO> dataList = new ArrayList<>();

        new OrderDAO().getList(uid, dataList, new OnGetListOrderListener() {
            @Override
            public void onGetListOrderSuccess() {
                new AsyncTask_History(recyclerView,progressBar,getContext(),dataList).execute();
            }

            @Override
            public void onGetObjectSuccess() {

            }
        });

        return view;
    }
}