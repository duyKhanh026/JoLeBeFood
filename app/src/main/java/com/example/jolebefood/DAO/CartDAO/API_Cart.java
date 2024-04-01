package com.example.jolebefood.DAO.CartDAO;

import com.example.jolebefood.DTO.CartDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Cart {
    @GET("Cart.json")
    Call<HashMap<String, CartDTO>> getcart();


    @PUT("/Cart/{id}/{news}.json")
    Call<CartDTO> setData(@Path("id") String s1, @Path("news") String s2, @Body CartDTO object);

    @DELETE("/Cart/{id}/{news}.json")
    Call<Void> deleteData(@Path("id") String s1, @Path("news") String s2, @Body CartDTO object);
}
