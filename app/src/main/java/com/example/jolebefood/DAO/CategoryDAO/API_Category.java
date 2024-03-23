package com.example.jolebefood.DAO.CategoryDAO;

import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Category {

    @GET("Category.json")
    Call<HashMap<String, CategoryDTO>> getcategorylist();

    @PUT("/Category/{new}.json")
    Call<CategoryDTO> setData(@Path("new") String s1, @Body CategoryDTO object);


    // Tạo thử ai muốn dùng thì dùng như đối với app này thì không cần dùng hàm delete này
    @DELETE("/Discount/{id}.json")
    Call<Void> deleteData(@Path("id") String makm);
}
