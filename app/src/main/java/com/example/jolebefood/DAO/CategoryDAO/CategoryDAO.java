package com.example.jolebefood.DAO.CategoryDAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.User;
import com.example.jolebefood.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDAO {
    private CallRetrofit retrofit;

    private final String TAG = "Kien Test DiscountDAO";

    private API api;

    public CategoryDAO(CallRetrofit retrofit) {
        this.retrofit = retrofit;

    }

    public CategoryDAO() {
        retrofit = new CallRetrofit();

        if (retrofit != null) {
            this.api = retrofit.getRetrofit().create(API.class); // Sử dụng giao diện
        } else {
            // Xử lý trường hợp Retrofit instance bị null
            Log.e(TAG, "Retrofit instance is null");
        }
    }

    public void getList(ArrayList<CategoryDTO> list, OnGetListCategoryListener listener) {
        Call<List<CategoryDTO>> call = api.getData();
        call.enqueue(new Callback<List<CategoryDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryDTO>> call, Response<List<CategoryDTO>> response) {
                if (response.isSuccessful()) {
                    List<CategoryDTO> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        for (int i = 1; i < data.size(); i++) {
                            list.add(data.get(i));
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
            public void onFailure(Call<List<CategoryDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void AddCategory(CategoryDTO categoryDTO, Context context){
        Call<CategoryDTO> call1 = api.setData(categoryDTO.getMaDM(), categoryDTO);
        call1.enqueue(new Callback<CategoryDTO>() {
            @Override
            public void onResponse(Call<CategoryDTO> call, Response<CategoryDTO> response) {
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CategoryDTO> call, Throwable t) {
                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
