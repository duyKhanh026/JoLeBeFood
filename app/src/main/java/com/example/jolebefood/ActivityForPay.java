package com.example.jolebefood;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Cart_Item;
import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.AdapterRecycleView.Pay_Details_Item;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DAO.RegisterDAO.OnGetRegiterListener;
import com.example.jolebefood.DAO.RegisterDAO.Register_DAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.DTO.UserDTO;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActivityForPay extends AppCompatActivity {
    private RecyclerView recyclerView;

    private TextView txtName,txtPhone,txtDiaChi,txtTongTien,txtGiamGia,txtThanhTien,txtPhuongThuc,txtThoiGianDat,txtDiscount;

    private Button btnThanhToan;

    private Pay_Details_Item adapter;

    private UserDTO userDTO;

    private ArrayList<CartDTO> datalist;

    private String UID;

    NumberFormat currencyFormat;


    // Định dạng mong muốn (ví dụ: "yyyy-MM-dd HH:mm:ss")
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_pay);

        datalist = new ArrayList<>();
        // Tạo một đối tượng NumberFormat với định dạng tiền tệ và quốc gia
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        userDTO = new UserDTO();

        // Nhận Intent mà đã gửi từ Activity trước
        Intent intent = getIntent();

        UID = intent.getStringExtra("UID");

        AnhXa();

        SetDataLichSu();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                txtThoiGianDat.setText(dateFormat.format(currentTimestamp));
            }
        }, 0, 1, TimeUnit.SECONDS); // Lặp lại mỗi giây


        txtPhuongThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialog(ActivityForPay.this);
            }
        });



    }



    @SuppressLint("WrongViewCast")
    public void AnhXa(){
        recyclerView = findViewById(R.id.listSP_CTDH_pay);
        txtName = findViewById(R.id.txtName_CT_pay);
        txtPhone = findViewById(R.id.txtPhone_CT_pay);
        txtDiaChi = findViewById(R.id.txtDiaChi_CT_pay);
        txtTongTien = findViewById(R.id.txtTongTien_CTHD_pay);
        txtGiamGia = findViewById(R.id.txtGiamGia_CTHD_pay);
        txtThanhTien = findViewById(R.id.txtThanhTien_CTHD_pay);
        txtPhuongThuc = findViewById(R.id.txtPhuongThuc_CTHD_pay);
        txtThoiGianDat = findViewById(R.id.txtThoiGianDat_CTHD_pay);
        btnThanhToan = findViewById(R.id.btnThanhToan_CTHD_pay);
        txtDiscount = findViewById(R.id.txtDiscount_CTHD_pay);

        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityForPay.this));

    }

    public void SetDataLichSu() {
        new CartDAO().getList(UID, datalist, new OnGetListCartListener() {
            @Override
            public void onGetListCartSuccess() {

                adapter = new Pay_Details_Item(datalist);

                recyclerView.setAdapter(adapter);

                int totalAmount = calculateTotalAmount(datalist);
                txtThanhTien.setText(currencyFormat.format(totalAmount));

                int discountFee = 0;
                txtGiamGia.setText(currencyFormat.format(discountFee));

                int totalPayAmount = totalAmount - discountFee;
                txtTongTien.setText(currencyFormat.format(totalPayAmount));




            }
            @Override
            public void onGetObjectSuccess() {
            }
        });

        new Register_DAO().getUserObject(UID, userDTO, new OnGetRegiterListener() {
                @Override
                public void OnSentGmail() {

                }

                @Override
                public void GetUserSuccess() {
                    txtName.setText(userDTO.getName());
                    txtPhone.setText(userDTO.getPhone());
                    txtDiaChi.setText(userDTO.getAddress());
                }
        });


    }

    private int calculateTotalAmount(ArrayList<CartDTO> cartItems) {
        int totalAmount = 0;
        for (CartDTO item : cartItems) {
            totalAmount += item.getTongTien();
        }
        return totalAmount;
    }

    private void ShowDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_choose_pay);

        Log.e("Kien 1","kkisdfsdf");

        RelativeLayout Item_Momo = dialog.findViewById(R.id.Item_MoMo_Choose_pay);
        RelativeLayout Item_Bth = dialog.findViewById(R.id.Item_Bth_Choose_pay);

        Item_Bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhuongThuc.setText("Thanh toán khi nhận hàng");
                dialog.dismiss();
            }
        });

        Item_Momo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtPhuongThuc.setText("Thanh toán bằng MoMo");
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}
