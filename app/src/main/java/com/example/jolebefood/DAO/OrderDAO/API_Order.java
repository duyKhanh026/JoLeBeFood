package com.example.jolebefood.DAO.OrderDAO;

import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.OrderDetailsDTO;
import com.example.jolebefood.OrderDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @PUT("/Order/{id}/{new}.json")
    Call<OrderDTO> setData(@Path("id") String id, @Path("new") String s1, @Body OrderDTO object);


}
