package com.example.jolebefood.DAO.DiscountDAO;

import com.example.jolebefood.DTO.DiscountDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API_Discount {
    @GET("Discount.json")
        // Thay đổi đường dẫn tùy theo cấu trúc thư mục của bạn
    Call<List<DiscountDTO>> getData();

    @PUT("/Discount/{new}.json")
    Call<DiscountDTO> setData(@Path("new") String s1, @Body DiscountDTO object);


    // Tạo thử ai muốn dùng thì dùng như đối với app này thì không cần dùng hàm delete này
    @DELETE("/Discount/{id}.json")
    Call<Void> deleteData(@Path("id") String makm);
}
