package com.example.jolebefood.DAO.OrderDAO;

import android.content.Context;
import android.util.Log;

import com.example.jolebefood.DAO.CallRetrofit;
import com.example.jolebefood.DTO.OrderDTO;

import java.util.ArrayList;
import java.util.List;

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

    public void getList(ArrayList<OrderDTO> list, OnGetListOrderListener listener) {
        Call<List<OrderDTO>> call = api.getData();
        call.enqueue(new Callback<List<OrderDTO>>() {
            @Override
            public void onResponse(Call<List<OrderDTO>> call, Response<List<OrderDTO>> response) {
                if (response.isSuccessful()) {
                    List<OrderDTO> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        for (int i = 1; i < data.size(); i++) {
                            list.add(data.get(i));
                        }
                        listener.onGetListOrderSuccess(list);
                    } else {
                        Log.e(TAG, "Data rỗng hoặc không hợp lệ.");
                    }
                } else {
                    Log.e(TAG, "Failed to get data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<OrderDTO>> call, Throwable t) {
                Log.e(TAG, "Error getting data: " + t.getMessage());
            }
        });
    }

    public void SetDataOrder(OrderDTO orderDTO, Context context){
        Call<OrderDTO> call1 = api.setData(orderDTO.getMaDH(), orderDTO);
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

    public void DeleteOrder(String id){
        // Gọi phương thức deleteData() với orderId cụ thể
        Call<Void> call = api.deleteData(id);

        // Thực hiện cuộc gọi bất đồng bộ
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xóa thành công
                    Log.d(TAG, "Deleted successfully");
                } else {
                    // Xóa không thành công, xử lý lỗi
                    Log.e(TAG, "Delete failed with error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xảy ra lỗi trong quá trình thực hiện cuộc gọi
                Log.e(TAG, "Failed to delete", t);
            }
        });
    }

}
