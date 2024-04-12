package com.example.jolebefood.DAO.ProductDAO;

import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Product {

    @GET("Product.json")
    Call<HashMap<String, ProductDTO>> getproduct();


    @PUT("/Product/{new}.json")
    Call<ProductDTO> setData(@Path("new") String id, @Body ProductDTO object);



    // Tạo thử ai muốn dùng thì dùng như đối với app này thì không cần dùng hàm delete này
    @DELETE("/Discount/{id}.json")
    Call<Void> deleteData(@Path("id") String makm);
}
