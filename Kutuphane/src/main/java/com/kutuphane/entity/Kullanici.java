package com.kutuphane.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kutuphane.entity.EBase;

@Entity
@Table(name = "KULLANICI")
public class Kullanici extends EBase {

	/**
		 * 
		 */
	private static final long serialVersionUID = 7213741601043691533L;

	private Long id;
	private String username;
	private String password;
	private String email;
	private Date sonBaglanti;

	@Id
	@GeneratedValue(generator = "seq_kullanici", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_kullanici", sequenceName = "seq_kullanici", allocationSize = 1, initialValue = 1)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username", unique = true, length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "son_baglanti_zamani")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSonBaglanti() {
		return sonBaglanti;
	}

	public void setSonBaglanti(Date sonBaglanti) {
		this.sonBaglanti = sonBaglanti;
	}

}
