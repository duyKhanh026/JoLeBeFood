package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.DAO.CategoryDAO.CategoryDAO;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class importCategory extends AppCompatActivity {
    String Ma_dm, Ten_dm;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.import_category);

        EditText madm, tendm, img_;

        madm = findViewById(R.id.MaDM_text);
        tendm = findViewById(R.id.TenDM_text);

        img_ = findViewById(R.id.IMGDM_text);

        Button nhapdm = findViewById(R.id.nhapDMBtn);

        nhapdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ma_dm = madm.getText().toString();
                Ten_dm = tendm.getText().toString();
                String imgURL = "/"+Ma_dm+".jpg";

                new CategoryDAO().SetDataCategory(new CategoryDTO(Ma_dm, Ten_dm,imgURL));
            }
        });
    }
}
