/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kutuphane.mavenproject1;

/**
 *
 * @author vektorel
 */
public class Kitaplar {

    public Kitaplar() {
    }

    public Kitaplar(String ad, String yazar, String yer, String kimde) {
        this.ad = ad;
        this.yazar = yazar;
        this.yer = yer;
        this.kimde = kimde;



    }
    String ad;
    String yazar;
    String kimde;
    String yer;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }

    public String getKimde() {
        return kimde;
    }

    public void setKimde(String kimde) {
        this.kimde = kimde;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }
}
