package com.example.jolebefood.DTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private String MaDH, SDT, MaKM, PhuongThucThanhToan;
    private int TongTien;
    private Timestamp ThoiGianDat, ThoiGianHoanThanh;


    public OrderDTO(String maDH, String SDT, String maKM, String phuongThucThanhToan, int tongTien, Timestamp thoiGianDat, Timestamp thoiGianHoanThanh) {
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

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public Timestamp getThoiGianDat() {
        return ThoiGianDat;
    }

    public void setThoiGianDat(Timestamp thoiGianDat) {
        ThoiGianDat = thoiGianDat;
    }

    public Timestamp getThoiGianHoanThanh() {
        return ThoiGianHoanThanh;
    }

    public void setThoiGianHoanThanh(Timestamp thoiGianHoanThanh) {
        ThoiGianHoanThanh = thoiGianHoanThanh;
    }


}
