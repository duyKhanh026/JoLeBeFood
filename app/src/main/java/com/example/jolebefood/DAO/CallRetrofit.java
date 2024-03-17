package com.example.jolebefood.DAO;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallRetrofit {
    private Retrofit retrofit;

    private final String BASE_URL = "https://jolebefood-default-rtdb.asia-southeast1.firebasedatabase.app/";


    private final String TAG = "Kien Test CallRetrofit2";

    public CallRetrofit() {
        try {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // URL của Firebase app
                    .addConverterFactory(GsonConverterFactory.create()) // Dùng để chuyển đổi tệp JSON thành đối tượng
                    .build();

        } catch (Exception e) {
            // Xử lý ngoại lệ khi tạo Retrofit instance
            Log.e(TAG, "Error creating Retrofit instance: " + e.getMessage());
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
