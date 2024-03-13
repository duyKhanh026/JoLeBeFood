package com.example.jolebefood.DTO;

import java.time.LocalDateTime;

public class OrderDTO {
    private String MaDH, SDT, MaKM, PhuongThucThanhToan;
    private int TongTien;
    private LocalDateTime ThoiGianDat, ThoiGianHoanThanh;

    public OrderDTO(String maDH, String SDT, String maKM, String phuongThucThanhToan, int tongTien, LocalDateTime thoiGianDat, LocalDateTime thoiGianHoanThanh) {
        MaDH = maDH;
        this.SDT = SDT;
        MaKM = maKM;
        PhuongThucThanhToan = phuongThucThanhToan;
        TongTien = tongTien;
        ThoiGianDat = thoiGianDat;
        ThoiGianHoanThanh = thoiGianHoanThanh;
    }


    public OrderDTO() {
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String maKM) {
        MaKM = maKM;
    }

    public String getPhuongThucThanhToan() {
        return PhuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        PhuongThucThanhToan = phuongThucThanhToan;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public LocalDateTime getThoiGianDat() {
        return ThoiGianDat;
    }

    public void setThoiGianDat(LocalDateTime thoiGianDat) {
        ThoiGianDat = thoiGianDat;
    }

    public LocalDateTime getThoiGianHoanThanh() {
        return ThoiGianHoanThanh;
    }

    public void setThoiGianHoanThanh(LocalDateTime thoiGianHoanThanh) {
        ThoiGianHoanThanh = thoiGianHoanThanh;
    }
}
