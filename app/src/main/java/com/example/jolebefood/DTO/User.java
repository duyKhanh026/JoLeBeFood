package com.example.jolebefood.DTO;

import java.util.Date;

public class User {
    private String SDT, MatKhau, HoTen, Email, DiaChi;
    private Date NgaySinh;

    public User(String SDT, String matKhau, String hoTen, String email, String diaChi, Date ngaySinh) {
        this.SDT = SDT;
        MatKhau = matKhau;
        HoTen = hoTen;
        Email = email;
        DiaChi = diaChi;
        NgaySinh = ngaySinh;
    }

    public User() {
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }
}
