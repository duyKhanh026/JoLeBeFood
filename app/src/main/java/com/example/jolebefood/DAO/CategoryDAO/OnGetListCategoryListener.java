package com.example.jolebefood.DAO.CategoryDAO;

import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.List;

public interface OnGetListCategoryListener {
    void onGetListCategorySuccess(List<CategoryDTO> list);
}
