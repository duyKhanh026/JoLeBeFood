package com.example.jolebefood.DTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO implements Serializable{
    private String MaDH, MaKH, MaKM, PhuongThucThanhToan;
    private int TongTien;
    private Timestamp ThoiGianDat, ThoiGianHoanThanh;

    private List<OrderDetailsDTO> listOrderDetails;

    private String DiaChiGiaoHang;

    public OrderDTO(String maDH, String MaKH, String maKM, String phuongThucThanhToan, int tongTien, Timestamp thoiGianDat, Timestamp thoiGianHoanThanh, List<OrderDetailsDTO> listOrderDetails, String DiaChiGiaoHang) {
        MaDH = maDH;
        this.MaKH = MaKH;
        MaKM = maKM;
        PhuongThucThanhToan = phuongThucThanhToan;
        TongTien = tongTien;
        ThoiGianDat = thoiGianDat;
        ThoiGianHoanThanh = thoiGianHoanThanh;
        this.listOrderDetails = listOrderDetails;
        this.DiaChiGiaoHang = DiaChiGiaoHang;
    }


    public OrderDTO() {
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
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

    public List<OrderDetailsDTO> getListOrderDetails() {
        return listOrderDetails;
    }

    public void setListOrderDetails(List<OrderDetailsDTO> listOrderDetails) {
        this.listOrderDetails = listOrderDetails;
    }

    public String getDiaChiGiaoHang() {
        return DiaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        DiaChiGiaoHang = diaChiGiaoHang;
    }
}
