package com.kutuphane.bean;

import java.util.List;

import com.kutuphane.entity.Kitaplar;

public class KitaplarPage {

	public KitaplarPage(List<Kitaplar> liste,int kayitSayisi) {
		this.kayitSayisi=kayitSayisi;
		this.liste=liste;
	}
	
	List<Kitaplar> liste;
	int kayitSayisi;

	public List<Kitaplar> getListe() {
		return liste;
	}

	public void setListe(List<Kitaplar> liste) {
		this.liste = liste;
	}

	public int getKayitSayisi() {
		return kayitSayisi;
	}

	public void setKayitSayisi(int kayitSayisi) {
		this.kayitSayisi = kayitSayisi;
	}

}
