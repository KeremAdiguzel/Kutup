package com.kutuphane.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class EBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7333863558541970990L;

	private String ekleyen;
	private String guncelleyen;
	private Date eklemeTarihi;
	private Date guncellemeTarihi;
	private Boolean kayitDurumu;

	@Column(name="ekleyen",length=100)
	public String getEkleyen() {
		return ekleyen;
	}

	public void setEkleyen(String ekleyen) {
		this.ekleyen = ekleyen;
	}

	@Column(name="guncelleyen",length=100)
	public String getGuncelleyen() {
		return guncelleyen;
	}

	public void setGuncelleyen(String guncelleyen) {
		this.guncelleyen = guncelleyen;
	}

	@Column(name="ekleme_tarihi")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEklemeTarihi() {
		return eklemeTarihi;
	}

	public void setEklemeTarihi(Date eklemeTarihi) {
		this.eklemeTarihi = eklemeTarihi;
	}

	@Column(name="guncelleme_tarihi")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getGuncellemeTarihi() {
		return guncellemeTarihi;
	}

	public void setGuncellemeTarihi(Date guncellemeTarihi) {
		this.guncellemeTarihi = guncellemeTarihi;
	}

	@Column(name="kayit_durumu")
	public Boolean getKayitDurumu() {
		return kayitDurumu;
	}

	public void setKayitDurumu(Boolean kayitDurumu) {
		this.kayitDurumu = kayitDurumu;
	}

}
