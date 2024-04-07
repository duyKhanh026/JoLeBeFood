package com.example.jolebefood;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DAO.RegisterDAO.OnGetRegiterListener;
import com.example.jolebefood.DAO.RegisterDAO.Register_DAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.DTO.UserDTO;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class OrderDetails extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView txtName,txtPhone,txtDiaChi,txtTongTien,txtGiamGia,txtThanhTien,txtPhuongThuc,txtThoiGianDat,txtThoiGianHT,txtDiscount;

    private Button btnThanhToan;

    private Order_Details_Item adapter;

    private OrderDTO orderDTO;

    private UserDTO userDTO;

    private DiscountDTO discountDTO;

    private String Type, ID, UID;

    private OrderDAO orderDAO;

    // Tạo một đối tượng SimpleDateFormat với định dạng mong muốn
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    // Lấy đơn vị tiền tệ dựa trên quốc gia (ở đây là Việt Nam)
    Currency currency = Currency.getInstance(new Locale("vi", "VN"));

    // Tạo một đối tượng NumberFormat với định dạng tiền tệ và quốc gia
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        orderDAO = new OrderDAO();

        orderDTO = new OrderDTO();

        userDTO = new UserDTO();

        discountDTO = new DiscountDTO();

        // Đặt đơn vị tiền tệ cho đối tượng định dạng
        currencyFormat.setCurrency(currency);

        // Nhận Intent mà đã gửi từ Activity trước
        Intent intent = getIntent();

        Type = intent.getStringExtra("Type");

        ID = intent.getStringExtra("ID");

        UID = intent.getStringExtra("UID");

        AnhXa();


        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Type.equals("LichSu")){

                    Toast.makeText(view.getContext(), "Thêm sản phẩm của đơn "+orderDTO.getMaDH()+" thành công",Toast.LENGTH_SHORT).show();

                    CartDAO cartDAO = new CartDAO();

                    for (OrderDetailsDTO orderDetails : orderDTO.getListOrderDetails()){
                        CartDTO cartDTO = new CartDTO(orderDetails.getMaMonAn(), orderDetails.getTenMonAn(), orderDetails.getSL(), "/"+orderDetails.getMaMonAn()+".jpg", orderDetails.getThanhTien());
                        cartDAO.SetData(view.getContext(),cartDTO, orderDTO.getMaKH());
                    }
                } else {
                    Toast.makeText(OrderDetails.this,"Thanh toán",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtPhuongThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Type.equals("ThanhToan")){
                    Toast.makeText(OrderDetails.this,"Chọn đi",Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Type.equals("ThanhToan")){
                    Intent intent1 = new Intent(OrderDetails.this,Discount.class);
                    startActivity(intent1);
                }
            }
        });

        // Set nội dung cho giao diện theo đơn hàng đã đặt
        SetDataLichSu();


    }

    public void AnhXa(){
        recyclerView = findViewById(R.id.listSP_CTDH);
        txtName = findViewById(R.id.txtName_CT);
        txtPhone = findViewById(R.id.txtPhone_CT);
        txtDiaChi = findViewById(R.id.txtDiaChi_CT);
        txtTongTien = findViewById(R.id.txtTongTien_CTHD);
        txtGiamGia = findViewById(R.id.txtGiamGia_CTHD);
        txtThanhTien = findViewById(R.id.txtThanhTien_CTHD);
        txtPhuongThuc = findViewById(R.id.txtPhuongThuc_CTHD);
        txtThoiGianDat = findViewById(R.id.txtThoiGianDat_CTHD);
        txtThoiGianHT = findViewById(R.id.txtThoiGianHT_CTHD);
        btnThanhToan = findViewById(R.id.btnThanhToan_CTHD);
        txtDiscount = findViewById(R.id.txtDiscount_CTHD);

        if (Type.equals("LichSu")){
            btnThanhToan.setText("Mua lại");
        } else {
            btnThanhToan.setText("Thanh toán");
        }
    }

    public void SetDataLichSu(){
        orderDAO.getOrderObject(UID, ID, orderDTO, new OnGetListOrderListener() {
            @Override
            public void onGetListOrderSuccess() {

            }

            @Override
            public void onGetObjectSuccess(){

                recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetails.this));

                adapter = new Order_Details_Item(orderDTO.getListOrderDetails());


                recyclerView.setAdapter(adapter);

                txtPhuongThuc.setText(orderDTO.getPhuongThucThanhToan());
                txtThoiGianDat.setText(sdf.format(orderDTO.getThoiGianDat()));
                txtThoiGianHT.setText(sdf.format(orderDTO.getThoiGianHoanThanh()));
                txtTongTien.setText(currencyFormat.format(orderDTO.getTongTien()));

                new Register_DAO().getUserObject(orderDTO.getMaKH(), userDTO, new OnGetRegiterListener() {
                    @Override
                    public void OnSentGmail() {
                    }

                    @Override
                    public void GetUserSuccess() {
                        txtDiaChi.setText(userDTO.getAddress());
                        txtName.setText(userDTO.getName());
                        txtPhone.setText(userDTO.getPhone());
                    }
                });

                if (orderDTO.getMaKM().equals("Không")){
                    Log.e("Kien Order Details",orderDTO.getMaKM());
                    txtGiamGia.setText("-"+currencyFormat.format(0));
                    txtThanhTien.setText(currencyFormat.format(orderDTO.getTongTien() - 0));
                    txtDiscount.setText(orderDTO.getMaKM());
                }
                else{
                    new DiscountDAO().getDiscountObject(orderDTO.getMaKM(), discountDTO, new OnGetListDiscountListener() {
                        @Override
                        public void onGetListDiscountSuccess(List<DiscountDTO> list) {

                        }

                        @Override
                        public void onGetObjectSuccess() {
                            txtGiamGia.setText("-"+currencyFormat.format(discountDTO.getGiatrikm()));
                            txtThanhTien.setText(currencyFormat.format(orderDTO.getTongTien() - discountDTO.getGiatrikm()));
                            txtDiscount.setText(discountDTO.getTenkm());
                        }
                    });
                }






            }
        });

    }
}