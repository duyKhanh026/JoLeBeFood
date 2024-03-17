package com.example.jolebefood.fragment.tab_home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.Discount;
import com.example.jolebefood.R;
import com.example.jolebefood.Users;
import com.example.jolebefood.fragment.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ImportDisCount extends AppCompatActivity {

    String Ma_km, Ten_km, pttt_km;
    int gt_km;
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

        nhapkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ma_km = makm.getText().toString();
                Ten_km = tenkm.getText().toString();
                gt_km = Integer.parseInt(gtkm.getText().toString());
                pttt_km = pttt.getText().toString();


                if (!Ma_km.isEmpty() && !Ten_km.isEmpty() && !pttt_km.isEmpty()){
                    DiscountDTO dc = new DiscountDTO(Ma_km, Ten_km, gt_km, pttt_km);

                    db = FirebaseDatabase.getInstance();

                    reference = db.getReference("Discount");

                    reference.child(Ma_km).setValue(dc).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            makm.setText("");
                            tenkm.setText("");
                            gtkm.setText("");
                            pttt.setText("");
                            Toast.makeText(ImportDisCount.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            };

        });

    }
}
