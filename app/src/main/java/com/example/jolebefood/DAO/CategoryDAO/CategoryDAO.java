package com.example.jolebefood.DAO.CategoryDAO;

import android.util.Log;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DTO.CategoryDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDAO {
    private CallRetrofit retrofit;

    private final String TAG = "Danh Test CategoryDAO";

    private API_Category api;

    public CategoryDAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;
    }

    public CategoryDAO(){
        retrofit = new CallRetrofit();
        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API_Category.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(ArrayList<CategoryDTO> list, OnGetListCategoryListener listener) {
        Call<HashMap<String,CategoryDTO>> call = api.getcategorylist();
        call.enqueue(new Callback<HashMap<String,CategoryDTO>>() {
            @Override
            public void onResponse(Call<HashMap<String,CategoryDTO>> call, Response<HashMap<String,CategoryDTO>> response) {
                if (response.isSuccessful()) {
                    HashMap<String,CategoryDTO> data = response.body();
                    if (data != null) {
                        for (Map.Entry<String, CategoryDTO> entry : data.entrySet()) {
                            CategoryDTO categoryDTO = entry.getValue();
                            list.add(categoryDTO);
                        }
                        listener.onGetListCategorySuccess(list);
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<HashMap<String,CategoryDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void SetDataCategory(CategoryDTO categoryDTO){
        Call<CategoryDTO> call1 = api.setData(categoryDTO.getMaDM(), categoryDTO);
        call1.enqueue(new Callback<CategoryDTO >() {
            @Override
            public void onResponse(Call<CategoryDTO > call, Response<CategoryDTO> response) {
                Log.e(TAG, "Test DIscount:"+categoryDTO.getMaDM());
            }

            @Override
            public void onFailure(Call<CategoryDTO > call, Throwable t) {
                Log.e(TAG, "Test DIscount thất bại:"+categoryDTO.getMaDM());
            }
        });
    }
}
