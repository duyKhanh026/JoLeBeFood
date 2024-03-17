package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;
import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.R;

import java.sql.Timestamp;
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

        ArrayList<OrderDTO> dataList = new ArrayList<>();


//        new CategoryDAO().getList(dataList, new OnGetListCategoryListener() {
//            @Override
//            public void onGetListDiscountSuccess(List<CategoryDTO> list) {
//                adapter = new Purchase_History_Item(dataList);
//                recyclerView.setAdapter(adapter);
//            }
//        });

        // Tạo một thời gian hiện tại
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Tạo và thêm 8 đối tượng OrderDTO vào danh sách dataList
        for (int i = 0; i < 8; i++) {
            String sdt = "012345678" + i; // Tạo số điện thoại khác nhau cho mỗi đối tượng
            String maKM = (i % 2 == 0) ? "KM001" : "KM002"; // Chọn mã khuyến mãi là KM001 hoặc KM002

            // Tạo list orderdetails
            List<OrderDetailsDTO> listOrderDetails = new ArrayList<>();
            listOrderDetails.add(new OrderDetailsDTO("SP001", "DH00" + (i + 1), 2, 100000));
            listOrderDetails.add(new OrderDetailsDTO("SP002", "DH00" + (i + 1), 1, 150000));

            OrderDTO order = new OrderDTO("DH00" + (i + 1), sdt, maKM, "COD", 500000 + i * 10000, currentTimestamp, currentTimestamp, listOrderDetails);
            dataList.add(order);
            new OrderDAO().SetDataOrder(order, view.getContext());
        }




        adapter = new Purchase_History_Item(dataList);
        recyclerView.setAdapter(adapter);


        return view;
    }
}