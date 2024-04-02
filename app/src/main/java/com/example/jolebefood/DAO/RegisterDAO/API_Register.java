package com.example.jolebefood.DAO.RegisterDAO;

import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Register {
    @GET("/User/{id}.json")
        // Thay đổi đường dẫn tùy theo cấu trúc thư mục của bạn
    Call<UserDTO> getData(@Path("id") String s1);


    @PUT("/User/{new}.json")
    Call<UserDTO> setData(@Path("new") String s1, @Body UserDTO object);
}
