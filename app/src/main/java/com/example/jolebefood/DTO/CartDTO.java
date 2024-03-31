package com.example.jolebefood.DTO;

public class CartDTO {
    private String TenMonAn, MaMonAn, Image;
    private int SL, TongTien;

    public CartDTO(String MaMonAn, String TenMonAn, int SL, String Image, int TongTien) {
        this.TenMonAn = TenMonAn;
        this.MaMonAn = MaMonAn;
        this.SL = SL;
        this.Image = Image;
        this.TongTien = TongTien;
    }

    public CartDTO() {
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        TenMonAn = tenMonAn;
    }

    public String getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        MaMonAn = maMonAn;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

}
