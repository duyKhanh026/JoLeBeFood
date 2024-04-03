package com.example.jolebefood.DTO;

import android.graphics.Bitmap;

public class ProductDTO {
    private String MaMonAn,TenMonAn,MoTa, MaDanhMuc;
    private int Gia, Soluongdaban;
    private String IMG;

    public ProductDTO() {
    }


    public ProductDTO(String maMonAn, String tenMonAn,String IMG, String moTa,int gia, String maDanhMuc,int soluongdaban) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        MoTa = moTa;
        Gia = gia;
        MaDanhMuc = maDanhMuc;
        Soluongdaban = soluongdaban;
        this.IMG = IMG;
    }

    public int getSoluongdaban() {
        return Soluongdaban;
    }

    public void setSoluongdaban(int soluongdaban) {
        Soluongdaban = soluongdaban;
    }

    public String getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        MaDanhMuc = maDanhMuc;
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

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }
}
