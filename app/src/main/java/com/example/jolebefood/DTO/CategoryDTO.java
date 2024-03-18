package com.example.jolebefood.DTO;

public class CategoryDTO {
    private String maDM,tenDM;
    private int image;

    public CategoryDTO(String maDM, String tenDM,int img) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.image = img;
    }
    public CategoryDTO(String maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.image = 0;
    }

    public CategoryDTO() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
