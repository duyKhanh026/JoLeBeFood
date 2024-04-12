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
    @GET("/Cart/{id}.json")
    Call<HashMap<String, CartDTO>> getCart(@Path("id") String id);

    @PUT("/Cart/{id}/{news}.json")
    Call<CartDTO> setData(@Path("id") String s1, @Path("news") String s2, @Body CartDTO object);

    @DELETE("/Cart/{id}/{MaMonAn}.json")
    Call<Void> deleteData(@Path("id") String id, @Path("MaMonAn") String MaMonAn);

    @DELETE("/Cart/{id}.json")
    Call<Void> deleteCart(@Path("id") String id);

    @GET("/Cart/{id}/{news}.json")
    Call<CartDTO> getCartObject(@Path("id") String id, @Path("MaMonAn") String MaMonAn);
}
