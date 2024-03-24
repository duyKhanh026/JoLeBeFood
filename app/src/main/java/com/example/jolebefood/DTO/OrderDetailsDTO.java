package com.example.jolebefood.DTO;

public class OrderDetailsDTO {
    private String MaMonAn, MaDH, TenMonAn;
    private int SL, ThanhTien;

    public OrderDetailsDTO(String maMonAn, String maDH, String tenMonAn, int SL, int thanhTien) {
        MaMonAn = maMonAn;
        MaDH = maDH;
        TenMonAn = tenMonAn;
        this.SL = SL;
        ThanhTien = thanhTien;
    }

    public OrderDetailsDTO() {
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        MaMonAn = maMonAn;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }
}
