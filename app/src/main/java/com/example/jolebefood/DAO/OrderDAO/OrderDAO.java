package com.example.jolebefood.DAO.OrderDAO;

import android.content.Context;
import android.util.Log;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DTO.OrderDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void SetDataOrder(String id, OrderDTO orderDTO, Context context){
        Call<OrderDTO> call1 = api.setData(id,orderDTO.getMaDH(), orderDTO);
        call1.enqueue(new Callback<OrderDTO >() {
            @Override
            public void onResponse(Call<OrderDTO > call, Response<OrderDTO> response) {
                Log.e(TAG, "Kiên test add order:"+orderDTO.getMaDH());
            }

            @Override
            public void onFailure(Call<OrderDTO > call, Throwable t) {
                Log.e(TAG, "Kiên test add order thất bại:"+orderDTO.getMaDH());
            }
        });
    }
}
