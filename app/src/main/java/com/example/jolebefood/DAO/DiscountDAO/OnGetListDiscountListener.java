package com.example.jolebefood.DAO.DiscountDAO;

import com.example.jolebefood.DTO.DiscountDTO;

import java.util.List;

public interface OnGetListDiscountListener {
    void onGetListDiscountSuccess(List<DiscountDTO> list);

    void onGetObjectSuccess();
}
