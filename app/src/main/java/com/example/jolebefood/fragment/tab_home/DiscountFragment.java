package com.example.jolebefood.fragment.tab_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.Activity.Discount;
import com.example.jolebefood.R;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.discount, container, false);





        ImageView discount_qc = view.findViewById(R.id.image_discount_qc);

        ImageView image_discount = view.findViewById(R.id.image_discount);
        TextView text_dc = view.findViewById(R.id.text_discount);
        ImageButton button_view_discount = view.findViewById(R.id.button_view_discount);
        FrameLayout layout_to_discount = view.findViewById(R.id.layout_to_discount);

        Button btnnhap = (Button) view.findViewById(R.id.adddc_btn);


        layout_to_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Discount.class);
                startActivity(intent);
            }
        });

        btnnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it1 = new Intent(getActivity(), ImportDisCount.class);
                startActivity(it1);

            }
        });
        discount_qc.setImageResource(R.drawable.picture_discount); // Đặt hình ảnh ban đầu

//        final Handler handler = new Handler();
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                // Thay đổi hình ảnh của ImageView
//                discount_qc.setImageResource(R.drawable.picture_category);
//
//                // Lặp lại sau 5 giây
//                handler.postDelayed(this, 3000);
//            }
//        };
//
//        handler.post(runnable); // Bắt đầu chuyển đổi tự động sau khi đặt hình ảnh ban đầu

        return view;
    }
}