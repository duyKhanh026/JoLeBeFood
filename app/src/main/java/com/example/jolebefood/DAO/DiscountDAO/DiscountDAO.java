package com.example.jolebefood.DAO.DiscountDAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DAO.OrderDAO.API_Order;
import com.example.jolebefood.DAO.DiscountDAO.OnGetListDiscountListener;
import com.example.jolebefood.DAO.RegisterDAO.OnGetRegiterListener;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Call<HashMap<String,DiscountDTO>> call = api.getdiscount();
        call.enqueue(new Callback<HashMap<String,DiscountDTO>>() {
            @Override
            public void onResponse(Call<HashMap<String,DiscountDTO>> call, Response<HashMap<String,DiscountDTO>> response) {
                if (response.isSuccessful()) {
                    HashMap<String,DiscountDTO> data = response.body();
                    if (data != null) {
                        for (Map.Entry<String, DiscountDTO> entry : data.entrySet()) {
                            DiscountDTO discountDTO = entry.getValue();
                            list.add(discountDTO);
                        }
                        listener.onGetListDiscountSuccess(list);
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<HashMap<String,DiscountDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void SetDataDiscount(DiscountDTO discountDTO){
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

    public void getDiscountObject(String id, DiscountDTO discountDTO, OnGetListDiscountListener listener) {
        Call<DiscountDTO> call = api.getdiscountobject(id);
        call.enqueue(new Callback<DiscountDTO>() {
            @Override
            public void onResponse(Call<DiscountDTO> call, Response<DiscountDTO> response) {
                if (response.isSuccessful()) {
                    DiscountDTO data = response.body();
                    if (data != null) {
                        discountDTO.setMakm(data.getMakm());
                        discountDTO.setTenkm(data.getTenkm());
                        discountDTO.setGiatrikm(data.getGiatrikm());
                        discountDTO.setPhuongthuctt(data.getPhuongthuctt());
                        listener.onGetObjectSuccess();
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DiscountDTO> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }
}
