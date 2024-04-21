package com.example.jolebefood.DAO.CartDAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
            this.api = retrofit.getRetrofit().create(API_Cart.class);
        } else {
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(String id, ArrayList<CartDTO> list, OnGetListCartListener listener) {
        Call<HashMap<String,CartDTO>> call = api.getCart(id);
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
                        listener.onGetListCartSuccess();
                    } else {
                        listener.onGetListCartEmpty();
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

    public void SetData(Context context, CartDTO cart, String id){
        Call<CartDTO> call1 = api.setData(id,cart.getMaMonAn(), cart);
        call1.enqueue(new Callback<CartDTO >() {
            @Override
            public void onResponse(Call<CartDTO > call, Response<CartDTO> response) {
                Toast.makeText(context, "Thêm sản phẩm vào giỏ thành công", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CartDTO > call, Throwable t) {
                Toast.makeText(context, "Thêm sản phẩm vào giỏ thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteData(CartDTO cart, String id) {
        Call<Void> call = api.deleteData(id, cart.getMaMonAn());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Item deleted successfully");
                } else {
                    Log.e(TAG, "Failed to delete item. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error deleting item: " + t.getMessage());
            }
        });
    }


    // Xóa giỏ hàng khi thanh toán xong
    public void deleteCart(String id) {
        Call<Void> call = api.deleteCart(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Cart deleted successfully");
                } else {
                    Log.e(TAG, "Failed to delete cart. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Error deleting cart: " + t.getMessage());
            }
        });
    }

}
