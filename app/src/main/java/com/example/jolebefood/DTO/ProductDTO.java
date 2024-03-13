package com.example.jolebefood.DTO;

import android.graphics.Bitmap;

public class ProductDTO {
    private String MaMonAn,TenMonAn,MoTa,MaKhuyenMai;
    private int Gia;

    private Bitmap IMG;

    public ProductDTO(String maMonAn, String tenMonAn, String moTa, String maKhuyenMai, int gia, Bitmap IMG) {
        MaMonAn = maMonAn;
        TenMonAn = tenMonAn;
        MoTa = moTa;
        MaKhuyenMai = maKhuyenMai;
        Gia = gia;
        this.IMG = IMG;
    }

    public ProductDTO() {
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

    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        MaKhuyenMai = maKhuyenMai;
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
