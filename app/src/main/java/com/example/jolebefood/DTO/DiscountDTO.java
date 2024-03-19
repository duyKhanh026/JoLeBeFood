package com.example.jolebefood.DTO;

public class DiscountDTO {

    String makm;
    String tenkm;
    int giatrikm;
    String phuongthuctt;

    public DiscountDTO(){

    }

    public DiscountDTO(String makm, String tenkm, int giatrikm, String phuongthuctt) {
        this.makm = makm;
        this.tenkm = tenkm;
        this.giatrikm = giatrikm;
        this.phuongthuctt = phuongthuctt;
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
