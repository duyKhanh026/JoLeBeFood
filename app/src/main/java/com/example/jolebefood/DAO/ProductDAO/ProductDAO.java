package com.example.jolebefood.DAO.ProductDAO;

import android.util.Log;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDAO {
    private CallRetrofit retrofit;

    private final String TAG = "Danh Test DiscountDAO";

    private API_Product api;

    public ProductDAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;
    }

    public ProductDAO(){
        retrofit = new CallRetrofit();
        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API_Product.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(ArrayList<ProductDTO> list, OnGetListProductListener listener) {
        Call<HashMap<String,ProductDTO>> call = api.getproduct();
        call.enqueue(new Callback<HashMap<String,ProductDTO>>() {
            @Override
            public void onResponse(Call<HashMap<String,ProductDTO>> call, Response<HashMap<String,ProductDTO>> response) {
                if (response.isSuccessful()) {
                    HashMap<String,ProductDTO> data = response.body();
                    if (data != null) {
                        for (Map.Entry<String, ProductDTO> entry : data.entrySet()) {
                            ProductDTO productDTO = entry.getValue();
                            list.add(productDTO);
                        }
                        listener.onGetListProductSuccess(list);
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<HashMap<String,ProductDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void SetDataProduct(ProductDTO productDTO){
        Call<ProductDTO> call1 = api.setData(productDTO.getMaMonAn() ,productDTO);
        call1.enqueue(new Callback<ProductDTO >() {
            @Override
            public void onResponse(Call<ProductDTO > call, Response<ProductDTO> response) {
                Log.e(TAG, "Test DIscount:"+productDTO.getMaMonAn());
            }

            @Override
            public void onFailure(Call<ProductDTO > call, Throwable t) {
                Log.e(TAG, "Test DIscount thất bại:"+productDTO.getMaMonAn());
            }
        });
    }



}
