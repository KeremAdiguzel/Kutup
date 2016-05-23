package com.kutuphane.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.kutuphane.base.DbException;
import com.kutuphane.base.Sort;
import com.kutuphane.entity.Kullanici;
import com.kutuphane.service.KullaniciService;

@ManagedBean(name = "kullaniciBean")
@ViewScoped
public class KullaniciBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4888413859138119288L;

	private KullaniciService kullaniciService;
	private Kullanici kullanici;
	private LazyDataModel<Kullanici> lazy;

	@PostConstruct
	public void init() {
		kullaniciService = new KullaniciService();
		listele();
	}

	@SuppressWarnings("serial")
	public void listele() {

		lazy = new LazyDataModel<Kullanici>() {

			@SuppressWarnings("incomplete-switch")
			@Override
			public List<Kullanici> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				Sort mySorting = Sort.ASC;
				if (sortField != null && !sortField.equals("")) {
					switch (sortOrder) {
					case ASCENDING:
						mySorting = Sort.ASC;
						break;
					case DESCENDING:
						mySorting = Sort.DESC;
						break;
					}
				}

				KitaplarPage kullaniciPage = kullaniciService.getByPaging(
						first, pageSize, filters, sortField, mySorting);
				lazy.setRowCount(kullaniciPage.kayitSayisi);
				return null;
			}

		};

	}

	public LazyDataModel<Kullanici> getLazy() {
		return lazy;
	}

	public void sil(Long id) {
		try {
			kullaniciService.deleteById(id);
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Kayýt", "Kullanýcý Silindi"));
	}

	public void kaydet() {
		try {
			kullaniciService.save(kullanici);
			listele();
		} catch (DbException e) {
			e.printStackTrace();
		}
		kullanici = new Kullanici();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Kayýt", "Kullanýcý Kaydedildi"));
	}

	public Kullanici getKullanici() {
		if (kullanici == null) {
			kullanici = new Kullanici();
		}
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

}
