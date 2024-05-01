package com.example.jolebefood.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AsyncTask.AsyncTask_PayDetails;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DAO.DiscountDAO.DiscountDAO;
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
import com.example.jolebefood.R;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.example.jolebefood.SignIn_and_SignUp.Intro;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class ActivityForPay extends AppCompatActivity {

    // Tọa độ của quán
    private final static double latitude_Quan = 10.846364740875595;
    private final static double longitude_Quan = 106.67100464207535;

    private RecyclerView recyclerView;

    private TextView txtName,txtPhone,txtDiaChi,txtTongTien,txtGiamGia,txtThanhTien,txtPhuongThuc,txtDiscount,txtPhiGiao,txtKhoangCach,txtPhiGH_CT;

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

    private double latitude, longitude;

    NumberFormat currencyFormat;

    private int PhiGiaoHang = 0, DiscountFee = 0;


    // Định dạng mong muốn (ví dụ: "yyyy-MM-dd HH:mm:ss")
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_pay);

        KhoiTao();

        AnhXa();

        ChuyenActivity();


        SetData();

        txtDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(ActivityForPay.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ActivityForPay.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ActivityForPay.this,"App chưa được cấp quyền truy cập vị trí",Toast.LENGTH_SHORT).show();
                    return;
                }

                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(location -> {
                            if (location != null) {
                                // Xử lý vị trí hiện tại
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                double distance = calculateDistance(latitude,longitude);

                                PhiGiaoHang = (int) (distance * 5000);

                                txtDiaChi.setText(getAddress(latitude,longitude));

                                DecimalFormat df = new DecimalFormat("#.##");
                                String formattedDistance = df.format(distance);

                                // Tính khoảng cách giữa hai điểm
                                txtKhoangCach.setText(formattedDistance);

                                txtPhiGH_CT.setText(currencyFormat.format(PhiGiaoHang));

                                txtPhiGiao.setText(currencyFormat.format(PhiGiaoHang));

                                int totalAmount = calculateTotalAmount(datalist) + PhiGiaoHang - DiscountFee;
                                txtTongTien.setText(currencyFormat.format(totalAmount));


                            } else {
                                Toast.makeText(ActivityForPay.this, "Bạn hãy bật GPS của điện thoại lên", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

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
                intent1.putExtra("latitude",latitude);
                intent1.putExtra("longitude",longitude);
                startActivity(intent1);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityForPay.this,Cart.class);
                startActivity(intent);
            }
        });

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtDiaChi.getText().toString().trim().equals("Nhấn để lấy vị trí hiện tại")){
                    Toast.makeText(ActivityForPay.this,"Vui lòng chọn địa điểm giao hàng",Toast.LENGTH_SHORT).show();
                    return;
                }
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

                    orderObject.setTongTien(calculateTotalAmount(datalist) + PhiGiaoHang - DiscountFee);

                    orderObject.setDiaChiGiaoHang(txtDiaChi.getText().toString().trim());

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

                    new CartDAO().deleteCart(UID);

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

        // Khởi tạo FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);




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
        txtKhoangCach = findViewById(R.id.txtKhoangCach_CTHD_pay);
        txtPhiGH_CT = findViewById(R.id.txtPhiGH_CTHD_pay);
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


                txtGiamGia.setText("- " + currencyFormat.format(DiscountFee));

                int totalPayAmount = totalAmount + PhiGiaoHang - DiscountFee;
                txtTongTien.setText(currencyFormat.format(totalPayAmount));

            }
            @Override
            public void onGetListCartEmpty() {
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

        RelativeLayout Item_VNPay = dialog.findViewById(R.id.Item_VNPay_Choose_pay);
        RelativeLayout Item_Bth = dialog.findViewById(R.id.Item_Bth_Choose_pay);

        if (discountDTO != null){
        }

        Item_Bth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (discountDTO != null){
                    if (discountDTO.getPhuongthuctt().equals("Thanh toán bằng VNPay")){
                        Toast.makeText(ActivityForPay.this,"Mã khuyến mãi chỉ áp dụng khi thanh toán bằng VNPay",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                txtPhuongThuc.setText("Thanh toán khi nhận hàng");
                dialog.dismiss();
            }
        });

        Item_VNPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (discountDTO != null){
                    if (discountDTO.getPhuongthuctt().equals("Thanh toán khi nhận hàng")){
                        Toast.makeText(ActivityForPay.this,"Mã khuyến mãi chỉ áp dụng thanh toán khi nhận hàng",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                txtPhuongThuc.setText("Thanh toán bằng VNPay");
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

    // Phương thức này sẽ lấy địa chỉ từ tọa độ vị trí (latitude và longitude)
    public String getAddress(double latitude, double longitude) {
        // Tạo một đối tượng Geocoder để chuyển đổi tọa độ thành địa chỉ
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        String addressText = ""; // Chuỗi để lưu trữ địa chỉ

        try {
            // Gọi phương thức getFromLocation để lấy danh sách địa chỉ từ tọa độ
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            // Kiểm tra xem danh sách địa chỉ có dữ liệu hay không
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0); // Lấy địa chỉ đầu tiên trong danh sách

                // Trích xuất thông tin chi tiết địa chỉ từ đối tượng Address
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n"); // Nối các dòng địa chỉ lại với nhau
                }
                addressText = sb.toString(); // Gán thông tin địa chỉ vào chuỗi addressText
            } else {
                addressText = "Không tìm thấy địa chỉ cho vị trí này";
            }
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình chuyển đổi địa chỉ
            e.printStackTrace();
            addressText = "Lỗi: " + e.getMessage();
        }

        // Trả về chuỗi chứa thông tin địa chỉ hoặc thông báo lỗi
        return addressText;
    }


    // Hàm để tính khoảng cách giữa hai điểm dựa trên tọa độ latitude và longitude
    public double calculateDistance(double lat1, double lon1) {
        Location startPoint = new Location("point A");
        startPoint.setLatitude(lat1);
        startPoint.setLongitude(lon1);

        Location endPoint = new Location("point B");
        endPoint.setLatitude(latitude_Quan);
        endPoint.setLongitude(longitude_Quan);

        // Tính khoảng cách giữa hai điểm tính bằng mét
        float distanceInMeters = startPoint.distanceTo(endPoint);

        // Chuyển đổi khoảng cách từ mét sang kilômét và giới hạn số chữ số sau dấu thập phân
        double distanceInKilometers = distanceInMeters / 1000.0;

        return distanceInKilometers;
    }


    public void ChuyenActivity(){
        // Nhận Intent mà đã gửi từ Activity trước
        Intent intent = getIntent();

        discountDTO = (DiscountDTO) intent.getSerializableExtra("DiscountDTO");

        UID = intent.getStringExtra("UID");

        PhuongThuc = intent.getStringExtra("PhuongThuc");

        latitude = intent.getDoubleExtra("latitude",0);

        longitude = intent.getDoubleExtra("longitude",0);


        if (PhuongThuc != null){
            txtPhuongThuc.setText(PhuongThuc);
        }

        if (discountDTO != null){
            txtDiscount.setText(discountDTO.getTenkm());
            DiscountFee = discountDTO.getGiatrikm();
        }

        if (latitude == 0 && longitude == 0){
            // Hiển thị thông báo
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityForPay.this);
            builder.setTitle("Thông báo")
                    .setMessage("Ứng dụng này cần truy cập vị trí của bạn để hoạt động. Vui lòng bật GPS trong cài đặt.")
                    .setPositiveButton("Bật GPS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        }
        else{
            double distance = calculateDistance(latitude,longitude);

            PhiGiaoHang = (int) (distance * 5000);

            txtDiaChi.setText(getAddress(latitude,longitude));

            DecimalFormat df = new DecimalFormat("#.##");
            String formattedDistance = df.format(distance);

            // Tính khoảng cách giữa hai điểm
            txtKhoangCach.setText(formattedDistance);

            txtPhiGH_CT.setText(currencyFormat.format(PhiGiaoHang));

            txtPhiGiao.setText(currencyFormat.format(PhiGiaoHang));
        }


    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (!isFinishing()) {
            Intent intent = new Intent(ActivityForPay.this,Cart.class);
            startActivity(intent);
            finish();
        }
    }


}
