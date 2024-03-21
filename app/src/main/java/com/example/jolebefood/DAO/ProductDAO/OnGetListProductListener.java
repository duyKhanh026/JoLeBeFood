package com.example.jolebefood.DAO.ProductDAO;

import com.example.jolebefood.DTO.DiscountDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.List;

public interface OnGetListProductListener {
    void onGetListProductSuccess(List<ProductDTO> list);
}
