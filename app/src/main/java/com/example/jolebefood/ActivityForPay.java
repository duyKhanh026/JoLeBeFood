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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.AdapterRecycleView.Pay_Details_Item;
import com.example.jolebefood.AsyncTask.AsyncTask_OrderDetails;
import com.example.jolebefood.AsyncTask.AsyncTask_PayDetails;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DAO.OrderDAO.OrderDAO;
import com.example.jolebefood.DAO.ProductDAO.OnGetListProductListener;
import com.example.jolebefood.DAO.ProductDAO.ProductDAO;
import com.example.jolebefood.DAO.RegisterDAO.OnGetRegiterListener;
import com.example.jolebefood.DAO.RegisterDAO.Register_DAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.DTO.UserDTO;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActivityForPay extends AppCompatActivity {
    private RecyclerView recyclerView;

    private TextView txtName,txtPhone,txtDiaChi,txtTongTien,txtGiamGia,txtThanhTien,txtPhuongThuc,txtThoiGianDat,txtDiscount,txtPhiGiao;

    private Button btnThanhToan;

    private ImageButton btnBack;

    private ProgressBar progressBar;

    private UserDTO userDTO;

    private DiscountDTO discountDTO;

    private ArrayList<CartDTO> datalist;

    private ArrayList<DiscountDTO> listDiscount;

    private ArrayList<OrderDTO> listOrder;

    private ArrayList<ProductDTO> listProduct;

    private OrderDTO orderObject;

    private String UID, PhuongThuc = "";

    NumberFormat currencyFormat;

    private final int PhiGiaoHang = 20000;


    // Định dạng mong muốn (ví dụ: "yyyy-MM-dd HH:mm:ss")
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_pay);

        KhoiTao();

        AnhXa();

        // Nhận Intent mà đã gửi từ Activity trước
        Intent intent = getIntent();

        discountDTO = (DiscountDTO) intent.getSerializableExtra("DiscountDTO");

        UID = intent.getStringExtra("UID");

        PhuongThuc = intent.getStringExtra("PhuongThuc");

        if (PhuongThuc != null){
            txtPhuongThuc.setText(PhuongThuc);
        }

        SetData();



        txtPhuongThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogPaymentMethod(ActivityForPay.this);
            }
        });

        txtDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityForPay.this, Discount.class);
                intent1.putExtra("UID",UID);
                intent1.putExtra("PhuongThuc",txtPhuongThuc.getText().toString());
                startActivity(intent1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtPhuongThuc.getText().toString().equals("Chọn phương thức thanh toán")){
                    Toast.makeText(ActivityForPay.this,"Vui lòng chọn phương thức thanh toán",Toast.LENGTH_SHORT).show();
                }
                else {
                    orderObject.setMaDH("DH"+(listOrder.size()+1));
                    orderObject.setMaKH(UID);
                    orderObject.setPhuongThucThanhToan(txtPhuongThuc.getText().toString());
                    if (!txtDiscount.getText().toString().equals("Chọn khuyến mãi")){
                        orderObject.setMaKM(discountDTO.getMakm());
                    }
                    else {
                        orderObject.setMaKM("Không");
                    }

                    // add list chi tiết đơn hàng
                    SetOrderDetails(datalist);

                    // Cập nhật chi tiết đơn hàng lên firebase
                    new OrderDAO().SetDataOrder(UID,orderObject,ActivityForPay.this);

                    // Giảm số lượng của mã khuyến mãi

                    discountDTO.setSoluong(discountDTO.getSoluong() - 1);

                    new DiscountDAO().SetDataDiscount(discountDTO);

                    // Tăng số lượng bán cho các món được mua
                    for (OrderDetailsDTO temp : orderObject.getListOrderDetails()){
                        TangSoLuongMua(temp);
                    }

                    //new CartDAO().deleteCart(UID);


                }
            }
        });

    }

    public void KhoiTao(){
        datalist = new ArrayList<>();

        listDiscount = new ArrayList<>();

        listOrder = new ArrayList<>();

        listProduct = new ArrayList<>();
        // Tạo một đối tượng NumberFormat với định dạng tiền tệ và quốc gia
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        userDTO = new UserDTO();

        orderObject = new OrderDTO();


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
        btnThanhToan = findViewById(R.id.btnThanhToan_CTHD_pay);
        txtDiscount = findViewById(R.id.txtDiscount_CTHD_pay);
        txtPhiGiao = findViewById(R.id.txtPhiGiaoHangCTHD_pay);
        btnBack = findViewById(R.id.button_back_pay);
        progressBar = findViewById(R.id.progressBar_for_pay);
        recyclerView.setLayoutManager(new LinearLayoutManager(ActivityForPay.this));

    }

    public void SetData() {
        new OrderDAO().getList(UID, listOrder, new OnGetListOrderListener() {
            @Override
            public void onGetListOrderSuccess() {
            }

            @Override
            public void onGetObjectSuccess() {

            }
        });

        new ProductDAO().getList(listProduct, new OnGetListProductListener() {
            @Override
            public void onGetListProductSuccess(List<ProductDTO> list) {

            }
        });


        // Lấy danh sách các món có trong giỏ hàng theo UID
        new CartDAO().getList(UID, datalist, new OnGetListCartListener() {
            @Override
            public void onGetListCartSuccess() {

                new AsyncTask_PayDetails(recyclerView,progressBar,ActivityForPay.this,datalist).execute();



                int totalAmount = calculateTotalAmount(datalist);
                txtThanhTien.setText(currencyFormat.format(totalAmount));

                txtPhiGiao.setText(currencyFormat.format(PhiGiaoHang));

                if (discountDTO != null){
                    txtDiscount.setText(discountDTO.getTenkm());

                    int discountFee = discountDTO.getGiatrikm();
                    txtGiamGia.setText("- " + currencyFormat.format(discountFee));

                    int totalPayAmount = totalAmount + PhiGiaoHang - discountFee;
                    txtTongTien.setText(currencyFormat.format(totalPayAmount));

                    orderObject.setTongTien(totalPayAmount);

                }
                else{
                    int discountFee = 0;
                    txtGiamGia.setText("- " + currencyFormat.format(discountFee));

                    int totalPayAmount = totalAmount + PhiGiaoHang - discountFee;
                    txtTongTien.setText(currencyFormat.format(totalPayAmount));

                    orderObject.setTongTien(totalPayAmount);
                }



            }
            @Override
            public void onGetObjectSuccess() {
            }
        });

        // Lấy thông tin người dùng
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



        // Set thời gian đặt
        Timestamp ThoiGianDat = new Timestamp(System.currentTimeMillis());
        orderObject.setThoiGianDat(ThoiGianDat);

        //Set thời gian hoàn thành
        Random random = new Random();
        long millisecondsInTenMinutes = (random.nextInt(6) + 7) * 60 * 1000; // 10 phút trong milliseconds
        Timestamp ThoiGianHoanThanh = new Timestamp(ThoiGianDat.getTime() + millisecondsInTenMinutes);
        orderObject.setThoiGianHoanThanh(ThoiGianHoanThanh);
    }


    private int calculateTotalAmount(ArrayList<CartDTO> cartItems) {
        int totalAmount = 0;
        for (CartDTO item : cartItems) {
            totalAmount += item.getTongTien();
        }
        return totalAmount;
    }

    private void ShowDialogPaymentMethod(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_choose_pay);

        RelativeLayout Item_Momo = dialog.findViewById(R.id.Item_MoMo_Choose_pay);
        RelativeLayout Item_Bth = dialog.findViewById(R.id.Item_Bth_Choose_pay);

        if (discountDTO != null){
        }

        Item_Bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (discountDTO != null){
                    if (discountDTO.getPhuongthuctt().equals("Thanh toán bằng MoMo")){
                        Toast.makeText(ActivityForPay.this,"Mã khuyến mãi chỉ áp dụng khi thanh toán bằng MoMo",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                txtPhuongThuc.setText("Thanh toán khi nhận hàng");
                dialog.dismiss();
            }
        });

        Item_Momo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (discountDTO != null){
                    if (discountDTO.getPhuongthuctt().equals("Thanh toán khi nhận hàng")){
                        Toast.makeText(ActivityForPay.this,"Mã khuyến mãi chỉ áp dụng thanh toán khi nhận hàng",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
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

    public void SetOrderDetails(ArrayList<CartDTO> list){
        List<OrderDetailsDTO> list_temp = new ArrayList<>();
        for (CartDTO c : list){
            OrderDetailsDTO a = new OrderDetailsDTO();
            a.setMaDH(orderObject.getMaDH());
            a.setMaMonAn(c.getMaMonAn());
            a.setTenMonAn(c.getTenMonAn());
            a.setSL(c.getSL());
            a.setThanhTien(c.getTongTien());
            list_temp.add(a);
        }
        orderObject.setListOrderDetails(list_temp);
    }

    public void TangSoLuongMua(OrderDetailsDTO detailsDTO){
        for (ProductDTO s : listProduct){
            if (s.getMaMonAn().equals(detailsDTO.getMaMonAn())){
                s.setSoluongdaban(s.getSoluongdaban() + detailsDTO.getSL());
                new ProductDAO().SetDataProduct(s);
            }
        }
    }

}
