package com.kutuphane.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.kutuphane.entity.Kullanici;
import com.kutuphane.service.KullaniciService;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private KullaniciService kullaniciService;
	
	String username;
	String pwd;
	
	@PostConstruct
	private void init() {
		kullaniciService=new KullaniciService();
	}
		
	public void girisYap() {
		
		if(username == null || username.equals("")){
			return;
		} 
		if(pwd == null || pwd.equals("")){
			return;
		}
		
		Kullanici girisKullanici = kullaniciService.getByUsername(username);
		
		if(girisKullanici!=null && girisKullanici.getPassword().equals(pwd)){
			//Session UserId Ekle
			FacesContext context = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	        session.setAttribute("UserId", girisKullanici.getId());
	        session.setAttribute("Username", girisKullanici.getUsername());
	        
			//Ana Sayfaya Yönlendir
	        try {
				context.getExternalContext().redirect("/Kutuphane/secure/kutuphane/kutuphane.xhtml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void cikisYap() {
		FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        session.setAttribute("UserId",null);
        session.setAttribute("Username", null);
        try {
			context.getExternalContext().redirect("/Kutuphane/login.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
