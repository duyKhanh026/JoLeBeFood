package com.example.jolebefood.DTO;

public class CategoryDTO {
    private String maDM,tenDM;
    private String IMG;

    public CategoryDTO(String maDM, String tenDM, String IMG) {
        this.maDM = maDM;
        this.tenDM = tenDM;
        this.IMG = IMG;
    }

    public CategoryDTO() {
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
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
