package com.example.jolebefood.DAO.CartDAO;

import android.util.Log;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DAO.CartDAO.API_Cart;
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DTO.CartDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartDAO {
    private CallRetrofit retrofit;

    private final String TAG = "Sang Test CartDAO";

    private API_Cart api;

    public CartDAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;
    }

    public CartDAO(){
        retrofit = new CallRetrofit();
        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API_Cart.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(ArrayList<CartDTO> list, OnGetListCartListener listener) {
        Call<HashMap<String,CartDTO>> call = api.getcart();
        call.enqueue(new Callback<HashMap<String,CartDTO>>() {
            @Override
            public void onResponse(Call<HashMap<String,CartDTO>> call, Response<HashMap<String,CartDTO>> response) {
                if (response.isSuccessful()) {
                    HashMap<String,CartDTO> data = response.body();
                    if (data != null) {
                        for (Map.Entry<String, CartDTO> entry : data.entrySet()) {
                            CartDTO cart = entry.getValue();
                            list.add(cart);
                        }
                        listener.onGetListCartSuccess(list);
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<HashMap<String,CartDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void SetData(CartDTO cart, String id){
        Call<CartDTO> call1 = api.setData(id,cart.getMaMonAn(), cart);
        call1.enqueue(new Callback<CartDTO >() {
            @Override
            public void onResponse(Call<CartDTO > call, Response<CartDTO> response) {
                Log.e(TAG, "Test Cart:"+cart.getMaMonAn());
            }

            @Override
            public void onFailure(Call<CartDTO > call, Throwable t) {
                Log.e(TAG, "Test Cart thất bại:"+cart.getMaMonAn());
            }
        });
    }


}
