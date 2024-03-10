package com.example.jolebefood.DTO;

public class KhuyenMaiDTO {

    String MaKM;
    String TenKM;
    int GiatriKM;
    String Phuongthuctt;
    boolean tinhtrangsudung;
    int img;

    public KhuyenMaiDTO(){

    }

    public KhuyenMaiDTO(String maKM, String tenKM, int giatriKM, String phuongthuctt, boolean tinhtrangsudung, int img) {
        MaKM = maKM;
        TenKM = tenKM;
        GiatriKM = giatriKM;
        Phuongthuctt = phuongthuctt;
        this.tinhtrangsudung = tinhtrangsudung;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public KhuyenMaiDTO(String maKM, String tenKM, int giatriKM, String phuongthuctt, boolean tinhtrangsudung) {
        MaKM = maKM;
        TenKM = tenKM;
        GiatriKM = giatriKM;
        Phuongthuctt = phuongthuctt;
        this.tinhtrangsudung = tinhtrangsudung;
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

    public boolean isTinhtrangsudung() {
        return tinhtrangsudung;
    }

    public void setTinhtrangsudung(boolean tinhtrangsudung) {
        this.tinhtrangsudung = tinhtrangsudung;
    }
}
