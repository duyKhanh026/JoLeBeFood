package com.example.jolebefood.DTO;

public class CategoryDTO {
    private String maDM,tenDM;

    public CategoryDTO(String maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    public CategoryDTO() {
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        tenDM = tenDM;
    }
}
