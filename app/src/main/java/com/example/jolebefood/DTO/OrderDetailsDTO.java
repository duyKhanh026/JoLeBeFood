package com.example.jolebefood.DTO;

public class OrderDetailsDTO {
    private String MaMonAn, MaDH;
    private int SL, ThanhTien;

    public OrderDetailsDTO(String maMonAn, String maDH, int SL, int thanhTien) {
        MaMonAn = maMonAn;
        MaDH = maDH;
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
}
