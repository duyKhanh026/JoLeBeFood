package com.example.jolebefood.DTO;

public class CartDTO {
    private String SDT, MaMonAn;
    private int SL;

    public CartDTO(String SDT, String maMonAn, int SL) {
        this.SDT = SDT;
        MaMonAn = maMonAn;
        this.SL = SL;
    }

    public CartDTO() {
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
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
}
