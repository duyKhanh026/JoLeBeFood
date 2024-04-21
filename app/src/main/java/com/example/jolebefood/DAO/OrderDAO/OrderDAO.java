package com.example.jolebefood.DAO.OrderDAO;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.Activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDAO {
    private CallRetrofit retrofit;

    private final String TAG = "Kien Test DiscountDAO";

    private API_Order api;

    public OrderDAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;

    }

    public OrderDAO() {
        retrofit = new CallRetrofit();

        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API_Order.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(String id, ArrayList<OrderDTO> list, OnGetListOrderListener listener) {
        Call<HashMap<String,OrderDTO>> call = api.getOrder(id);
        call.enqueue(new Callback<HashMap<String,OrderDTO>>() {
            @Override
            public void onResponse(Call<HashMap<String,OrderDTO>> call, Response<HashMap<String,OrderDTO>> response) {
                if (response.isSuccessful()) {
                    HashMap<String,OrderDTO> data = response.body();
                    if (data != null) {
                        for (Map.Entry<String, OrderDTO> entry : data.entrySet()) {
                            OrderDTO order = entry.getValue();
                            list.add(order);
                        }
                        listener.onGetListOrderSuccess();
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");

                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<HashMap<String,OrderDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void getOrderObject(String id, String MaDH, OrderDTO orderDTO, OnGetListOrderListener listener) {
        Call<OrderDTO> call = api.getOrderObject(id,MaDH);
        call.enqueue(new Callback<OrderDTO>() {
            @Override
            public void onResponse(Call<OrderDTO> call, Response<OrderDTO> response) {
                if (response.isSuccessful()) {
                    OrderDTO data = response.body();
                    if (data != null) {
                        orderDTO.setMaDH(data.getMaDH());
                        orderDTO.setMaKM(data.getMaKM());
                        orderDTO.setMaKH(data.getMaKH());
                        orderDTO.setThoiGianHoanThanh(data.getThoiGianHoanThanh());
                        orderDTO.setThoiGianDat(data.getThoiGianDat());
                        orderDTO.setListOrderDetails(data.getListOrderDetails());
                        orderDTO.setTongTien(data.getTongTien());
                        orderDTO.setPhuongThucThanhToan(data.getPhuongThucThanhToan());
                        orderDTO.setDiaChiGiaoHang(data.getDiaChiGiaoHang());
                        listener.onGetObjectSuccess();
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<OrderDTO> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }


    public void SetDataOrder(String id, OrderDTO orderDTO, Context context){
        Call<OrderDTO> call1 = api.setData(id,orderDTO.getMaDH(), orderDTO);
        call1.enqueue(new Callback<OrderDTO >() {
            @Override
            public void onResponse(Call<OrderDTO > call, Response<OrderDTO> response) {
                Toast.makeText(context,"Đơn hàng được tạo thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<OrderDTO > call, Throwable t) {
                Toast.makeText(context,"Đơn hàng được tạo thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
