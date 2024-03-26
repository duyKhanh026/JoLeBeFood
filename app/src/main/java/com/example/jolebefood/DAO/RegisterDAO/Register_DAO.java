package com.example.jolebefood.DAO.RegisterDAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DAO.OrderDAO.API_Order;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.UserDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_DAO {
    private CallRetrofit retrofit;

    private final String TAG = "Nam Test DiscountDAO";

    private API_Register api;

    public Register_DAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Register_DAO(){
        retrofit = new CallRetrofit();
        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API_Register.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void SetDataUser(String useridd ,UserDTO userDTO,OnGetRegiterListener listener){ // Ham them User
        Call<UserDTO> call1 = api.setData(useridd, userDTO);
        call1.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Log.e(TAG, "Nam test add order:"+userDTO.getName());
                listener.OnSentGmail();

            }

            @Override
            public void onFailure(Call<UserDTO > call, Throwable t) {
                Log.e(TAG, "Nam test add order thất bại:"+userDTO.getName());
            }
        });
    }
    public void SetDataUser2(String useridd ,UserDTO userDTO){
        Call<UserDTO> call1 = api.setData(useridd, userDTO);
        call1.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Log.e(TAG, "Nam test add order:"+userDTO.getName());
            }

            @Override
            public void onFailure(Call<UserDTO > call, Throwable t) {
                Log.e(TAG, "Nam test add order thất bại:"+userDTO.getName());
            }
        });
    }
}
