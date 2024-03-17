package com.example.jolebefood.fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscountFragment extends Fragment {

    View view;
    Discount_Item adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscountFragment newInstance(String param1, String param2) {
        DiscountFragment fragment = new DiscountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.discount, container, false);

        Button btnnhap = (Button) view.findViewById(R.id.adddc_btn);
        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it1 = new Intent(getActivity(), ImportDisCount.class);
                startActivity(it1);

            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.RecycleView_KhuyenMai);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("Giảm 10k");
        dataList.add("Giảm 15k");
        dataList.add("Giảm 25k");
        dataList.add("Giảm 20k");
        dataList.add("Giảm 30k");
        dataList.add("Giảm 40k");
        dataList.add("Giảm 50k");

        adapter = new Discount_Item(dataList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}