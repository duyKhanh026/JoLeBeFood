package com.example.jolebefood.DTO;

import android.graphics.Bitmap;

public class ProductDTO {
    private String MaMonAn,TenMonAn,MoTa;
    private int Gia, soLuomg;
    private Bitmap IMG;

    public ProductDTO(String maMonAn, String tenMonAn, String moTa, int sl,int gia) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        MoTa = moTa;
        soLuomg = sl;
        Gia = gia;

    }

    public ProductDTO() {
    }

    public int getSoLuomg() {
        return soLuomg;
    }

    public void setSoLuomg(int soLuomg) {
        this.soLuomg = soLuomg;
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public Bitmap getIMG() {
        return IMG;
    }

    public void setIMG(Bitmap IMG) {
        this.IMG = IMG;
    }
}
