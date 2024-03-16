package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;
import com.example.jolebefood.DAO.CategoryDAO.CategoryDAO;
import com.example.jolebefood.DAO.CategoryDAO.OnGetListCategoryListener;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;

    Purchase_History_Item adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyPageFragment() {
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
    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
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
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.activity_order_history, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.RecycleView_LichSu);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<CategoryDTO> dataList = new ArrayList<>();
        new CategoryDAO().getList(dataList, new OnGetListCategoryListener() {
            @Override
            public void onGetListDiscountSuccess(List<CategoryDTO> list) {
                adapter = new Purchase_History_Item(dataList);
                recyclerView.setAdapter(adapter);
            }
        });


        return view;
    }
}