package com.example.jolebefood.DAO.OrderDAO;

import com.example.jolebefood.DTO.OrderDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Order {
    @GET("Order.json")
        // Thay đổi đường dẫn tùy theo cấu trúc thư mục của bạn
    Call<List<OrderDTO>> getData();

    @PUT("/Order/{new}.json")
    Call<OrderDTO> setData(@Path("new") String s1, @Body OrderDTO object);


    // Tạo thử ai muốn dùng thì dùng như đối với app này thì không cần dùng hàm delete này
    @DELETE("/Order/{id}.json")
    Call<Void> deleteData(@Path("id") String orderId);

}
