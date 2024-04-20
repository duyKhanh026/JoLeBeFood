package com.example.jolebefood.DAO.OrderDAO;

import com.example.jolebefood.DTO.OrderDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Order {

//    @GET("OrderDetails/{id}.json")
//    Call<List<OrderDetailsDTO>> getOrderDetailsList(@Path("id") String id);


    @GET("/Order/{id}.json")
    // Thay đổi đường dẫn tùy theo cấu trúc thư mục của bạn
    // Lý do phải duùng HashMap là do cách lưu trữ của firebase thì Order đang là 1 object chứ không phải 1 list nên không thể getlist mà phải dùng hashmap để lấy key và value
    Call<HashMap<String,OrderDTO>> getOrder(@Path("id") String id);


    @GET("/Order/{id}/{MaDH}.json")
    Call<OrderDTO> getOrderObject(@Path("id") String id, @Path("MaDH") String MaDH);

    @PUT("/Order/{id}/{new}.json")
    Call<OrderDTO> setData(@Path("id") String id, @Path("new") String s1, @Body OrderDTO object);


}
