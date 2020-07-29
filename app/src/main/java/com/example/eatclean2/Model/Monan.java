package com.example.eatclean2.Model;

import java.io.Serializable;

public class Monan implements Serializable {
    int id;
    String ten;
    String mota;
    String image;
    int chot;

    public Monan(int id, String ten, String mota, String imgae,int chot) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.image = imgae;
        this.chot=chot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getImgae() {
        return image;
    }

    public void setImage(String imgae) {
        this.image = imgae;
    }

    public String getImage() {
        return image;
    }

    public int getChot() {
        return chot;
    }

    public void setChot(int chot) {
        this.chot = chot;
    }
}
