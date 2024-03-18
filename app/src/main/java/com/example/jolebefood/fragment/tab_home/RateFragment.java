package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RateFragment extends Fragment {
    String idCategory, nameCategory;
    String MaMonAn, TenMonAn, MoTa, MaKhuyenMai;
    int Gia, soLuomg;
    FirebaseDatabase db;
    DatabaseReference reference;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RateFragment() {
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
    public static RateFragment newInstance(String param1, String param2) {
        RateFragment fragment = new RateFragment();
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
        view = inflater.inflate(R.layout.fragment_rate, container, false);

        EditText CategoryID_text = view.findViewById(R.id.CategoryID_text);
        EditText Category_text = view.findViewById(R.id.Category_text);
        Button nhapCategoryBtn = view.findViewById(R.id.nhapCategoryBtn);

        EditText foodID_text = view.findViewById(R.id.foodID_text);
        EditText name_text = view.findViewById(R.id.name_text);
        EditText quantity_text = view.findViewById(R.id.quantity_text);
        EditText price_text = view.findViewById(R.id.price_text);
        EditText depr_text = view.findViewById(R.id.depr_text);
        Button nhapFoodBtn = view.findViewById(R.id.nhapFoodBtn);

//        CategoryDTO dm = new CategoryDTO("DM1", "Cơm Gà");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM2", "Cơm Sường");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM3", "Nước Uống");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM4", "Lẫu");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM5", "Mì Cay");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM6", "Trẻ Em");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM7", "Người Già");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM8", "Tao Giỡn");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM9", "Hủ Tiếu");
//        themDanhMuc(dm);
//        dm = new CategoryDTO("DM10", "Bún");
//        themDanhMuc(dm);
        nhapCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idCategory = CategoryID_text.getText().toString();
                nameCategory = Category_text.getText().toString();

                if (!idCategory.isEmpty() && !nameCategory.isEmpty()){
                    CategoryDTO users = new CategoryDTO(idCategory, nameCategory);

                    db = FirebaseDatabase.getInstance();

                    reference = db.getReference("Category"); // tương tự from table trong sql
                    reference.child(idCategory).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            CategoryID_text.setText("");
                            Category_text.setText("");
                            Toast.makeText(getContext(),"Successfuly Category updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        nhapFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaMonAn = foodID_text.getText().toString();
                TenMonAn = name_text.getText().toString();
                MoTa = depr_text.getText().toString();
                soLuomg = Integer.parseInt(quantity_text.getText().toString());
                Gia = Integer.parseInt(price_text.getText().toString());


//                if (!idCategory.isEmpty() && !nameCategory.isEmpty()){
//                    ProductDTO users = new ProductDTO(MaMonAn, TenMonAn, MoTa, soLuomg, Gia, );
//
//                    db = FirebaseDatabase.getInstance();
//
//                    reference = db.getReference("Product"); // tương tự from table trong sql
//                    reference.child(idCategory).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            CategoryID_text.setText("");
//                            Category_text.setText("");
//                            Toast.makeText(getContext(),"Successfuly Category updated", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
            }
        });
        return view;
    }
    public void themDanhMuc(CategoryDTO catedto) {
        db = FirebaseDatabase.getInstance();

        reference = db.getReference("Category"); // tương tự from table trong sql
        reference.child(catedto.getMaDM()).setValue(catedto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(),"Successfuly Category updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}