package com.example.jolebefood.DAO.DiscountDAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DAO.OrderDAO.API_Order;
import com.example.jolebefood.DAO.OrderDAO.OnGetListOrderListener;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.OrderDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class DiscountDAO {
    private CallRetrofit retrofit;

    private final String TAG = "Danh Test DiscountDAO";

    private API_Discount api;

    public DiscountDAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;
    }

    public DiscountDAO(){
        retrofit = new CallRetrofit();
        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API_Discount.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(ArrayList<DiscountDTO> list, OnGetListDiscountListener listener) {
        Call<List<DiscountDTO>> call = api.getData();
        call.enqueue(new Callback<List<DiscountDTO>>() {
            @Override
            public void onResponse(Call<List<DiscountDTO>> call, Response<List<DiscountDTO>> response) {
                if (response.isSuccessful()) {
                    List<DiscountDTO> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        for (int i = 1; i < data.size(); i++) {
                            list.add(data.get(i));
                        }
                        listener.onGetListDiscountSuccess(list);
                        System.out.println("Đã lấy được");
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<DiscountDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void SetDataDiscount(DiscountDTO discountDTO, Context context){
        Call<DiscountDTO> call1 = api.setData(discountDTO.getMakm(), discountDTO);
        call1.enqueue(new Callback<DiscountDTO >() {
            @Override
            public void onResponse(Call<DiscountDTO > call, Response<DiscountDTO> response) {
                Log.e(TAG, "Test DIscount:"+discountDTO.getMakm());
            }

            @Override
            public void onFailure(Call<DiscountDTO > call, Throwable t) {
                Log.e(TAG, "Test DIscount thất bại:"+discountDTO.getMakm());
            }
        });
    }
}
