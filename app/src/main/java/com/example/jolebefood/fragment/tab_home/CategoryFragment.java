package com.example.jolebefood.fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Category_Item;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.Product;
import com.example.jolebefood.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    View view;
    Category_Item adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
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
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        view = inflater.inflate(R.layout.fragment_category, container, false);

        Button xemsp;

        xemsp = view.findViewById(R.id.but_xemsp);
        xemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "đã chạy", Toast.LENGTH_SHORT).show();
                Intent it1 = new Intent(getActivity(), Product.class);
                startActivity(it1);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() , 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<CategoryDTO> dataList = new ArrayList<>();
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM1", "Cơm Gà", R.drawable.comga));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));
        dataList.add(new CategoryDTO("DM2", "Cơm Bò", R.drawable.combo));

        adapter = new Category_Item(getContext(),dataList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}