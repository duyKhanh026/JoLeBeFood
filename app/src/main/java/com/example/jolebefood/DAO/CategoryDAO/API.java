package com.example.jolebefood.DAO.CategoryDAO;

import com.example.jolebefood.DTO.CategoryDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {
//    @POST("/upload/{new}.json")
//    Call<Object> setData(@Path("new") String s1, @Body Object object);

    @GET("Danh mục.json")
        // Thay đổi đường dẫn tùy theo cấu trúc thư mục của bạn
    Call<List<CategoryDTO>> getData();

    @PUT("/Danh mục/{new}.json")
    Call<CategoryDTO> setData(@Path("new") String s1, @Body CategoryDTO object);

}
