package com.kutuphane.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Kitaplar")
public class Kitaplar extends EBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7925051696503239826L;

	private String ad;
	private String yazar;
	private String kimde;
	private String yer;
	private int id;

	@Id
	@GeneratedValue(generator = "seq_kitaplar", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, sequenceName = "seq_kitaplar", name = "seq_kitaplar")
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "kitapad")
	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	@Column(name = "yazar")
	public String getYazar() {
		return yazar;
	}

	public void setYazar(String yazar) {
		this.yazar = yazar;
	}

	@Column(name = "kimde")
	public String getKimde() {
		return kimde;
	}

	public void setKimde(String kimde) {
		this.kimde = kimde;
	}

	@Column(name = "yer")
	public String getYer() {
		return yer;
	}

	public void setYer(String yer) {
		this.yer = yer;
	}

}
