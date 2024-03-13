package com.example.jolebefood.DTO;

public class CategoryDTO {
    private String MaDM,TenDM;

    public CategoryDTO(String maDM, String tenDM) {
        MaDM = maDM;
        TenDM = tenDM;
    }

    public CategoryDTO() {
    }

    public String getMaDM() {
        return MaDM;
    }

    public void setMaDM(String maDM) {
        MaDM = maDM;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String tenDM) {
        TenDM = tenDM;
    }
}
