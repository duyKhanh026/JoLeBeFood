package com.example.jolebefood.DTO;

import java.io.Serializable;

public class DiscountDTO implements Serializable {

    String makm;
    String tenkm;
    int giatrikm;
    String phuongthuctt;

    int soluong;

    public DiscountDTO(){

    }

    public DiscountDTO(String makm, String tenkm, int giatrikm, String phuongthuctt, int soluong) {
        this.makm = makm;
        this.tenkm = tenkm;
        this.giatrikm = giatrikm;
        this.phuongthuctt = phuongthuctt;
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getTenkm() {
        return tenkm;
    }

    public void setTenkm(String tenkm) {
        this.tenkm = tenkm;
    }

    public int getGiatrikm() {
        return giatrikm;
    }

    public void setGiatrikm(int giatrikm) {
        this.giatrikm = giatrikm;
    }

    public String getPhuongthuctt() {
        return phuongthuctt;
    }

    public void setPhuongthuctt(String phuongthuctt) {
        this.phuongthuctt = phuongthuctt;
    }
}
