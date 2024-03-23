package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.ProductDAO.ProductDAO;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.C;

public class ImportDisCount extends AppCompatActivity {

    String Ma_km, Ten_km, pttt_km;
    int gt_km;

    String MaMonAn,TenMonAn,MoTa, MaDanhMuc;

    int Gia, SoLuomg, Soluongdaban;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.import_discount);

        EditText makm, tenkm, gtkm, pttt;
        makm = findViewById(R.id.KM_text);
        tenkm = findViewById(R.id.TenKM_text);
        gtkm = findViewById(R.id.GT_text);
        pttt = findViewById(R.id.pttt_text);
        Button nhapkm = findViewById(R.id.nhapDCBtn);
        Button nhappd = findViewById(R.id.nhapPDBtn);

        nhapkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ma_km = makm.getText().toString();
                Ten_km = tenkm.getText().toString();
                gt_km = Integer.parseInt(gtkm.getText().toString());
                pttt_km = pttt.getText().toString();

                new DiscountDAO().SetDataDiscount(new DiscountDTO(Ma_km,Ten_km,gt_km,pttt_km));
            };

        });

        EditText mapd, tenpd, gtpd, slpd, motapd, madanhmuc, soluong_daban;
        mapd = findViewById(R.id.PD_text);
        tenpd = findViewById(R.id.TenPD_text);
        gtpd = findViewById(R.id.GiaPD_text);
        slpd = findViewById(R.id.SLPD_text);
        motapd = findViewById(R.id.Mota_text);
        madanhmuc = findViewById(R.id.MaDM_text);
        soluong_daban = findViewById(R.id.soluong_daban_text);
        nhappd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaMonAn = mapd.getText().toString();
                TenMonAn = tenpd.getText().toString();
                Gia = Integer.parseInt(gtpd.getText().toString());
                SoLuomg = Integer.parseInt(slpd.getText().toString());
                Soluongdaban = Integer.parseInt(soluong_daban.getText().toString());
                MoTa = motapd.getText().toString();
                MaDanhMuc = madanhmuc.getText().toString();

                String imgURL = "/"+MaMonAn+".jpg";

                new ProductDAO().SetDataProduct(new ProductDTO(MaMonAn,TenMonAn,imgURL,MoTa,SoLuomg,Gia,MaDanhMuc,Soluongdaban));
            };

        });

    }
}
