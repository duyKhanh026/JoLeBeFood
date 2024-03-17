package com.example.jolebefood.DTO;

public class DiscountDTO {

    String MaKM;
    String TenKM;
    int GiatriKM;
    String Phuongthuctt;

    public DiscountDTO(){

    }

    public DiscountDTO(String maKM, String tenKM, int giatriKM, String phuongthuctt) {
        MaKM = maKM;
        TenKM = tenKM;
        GiatriKM = giatriKM;
        Phuongthuctt = phuongthuctt;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String maKM) {
        MaKM = maKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String tenKM) {
        TenKM = tenKM;
    }

    public int getGiatriKM() {
        return GiatriKM;
    }

    public void setGiatriKM(int giatriKM) {
        GiatriKM = giatriKM;
    }

    public String getPhuongthuctt() {
        return Phuongthuctt;
    }

    public void setPhuongthuctt(String phuongthuctt) {
        Phuongthuctt = phuongthuctt;
    }

}
