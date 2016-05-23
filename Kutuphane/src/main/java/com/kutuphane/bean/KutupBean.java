package com.kutuphane.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.kutuphane.base.Sort;
import com.kutuphane.entity.Kitaplar;
import com.kutuphane.bean.KitaplarPage;
import com.kutuphane.base.DbException;
import com.kutuphane.service.KutuphaneService;

@ManagedBean(name = "kutupBean")
@ViewScoped
public class KutupBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5935008797399108020L;

	private List<Kitaplar> kitaplarListe;
	private KutuphaneService kutuphaneService;
	private Kitaplar Kitaplar;
	private LazyDataModel<Kitaplar> lazy;

	@PostConstruct
	public void init() {

		kutuphaneService = new KutuphaneService();
		kitaplarListe = new ArrayList<Kitaplar>();

		kitaplarListe = kutuphaneService.getAll();

	}

	
	
	@SuppressWarnings("serial")
	public void listele() {
		
		lazy=new LazyDataModel<Kitaplar>() {
			
			@SuppressWarnings("incomplete-switch")
			@Override
			public List<Kitaplar> load(int first, int pageSize,String sortField, SortOrder sortOrder,Map<String, Object> filters) {
				Sort mySorting=Sort.ASC;
				if(sortField!=null && !sortField.equals("")){
					switch (sortOrder) {
					case ASCENDING:
						mySorting=Sort.ASC;
						break;
					case DESCENDING:
						mySorting=Sort.DESC;
						break;		
					}
				}
				
				KitaplarPage KitaplarPage = kutuphaneService.getByPaging(first,pageSize,filters,sortField,mySorting);
				lazy.setRowCount(KitaplarPage.kayitSayisi);
				return KitaplarPage.liste;
			}	
			
			
			
		};

	}
	
	public LazyDataModel<Kitaplar> getLazy() {
		return lazy;
	}
	
	public void sil(Long id) {
		try {
			kutuphaneService.deleteById(id);
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Kayýt",  "Kullanýcý Silindi") );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void save() {
		try {
			kutuphaneService.save(Kitaplar);
			kitaplarListe = kutuphaneService.getAll();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Kitaplar getKitaplar() {
		if (Kitaplar == null) {
			Kitaplar = new Kitaplar();
		}
		return Kitaplar;
	}

	public void setKitaplar(Kitaplar Kitaplar) {
		this.Kitaplar = Kitaplar;
	}

	public List<Kitaplar> getKitaplarListe() {
		return kitaplarListe;
	}

	public void KitaplarListe(List<Kitaplar> kitaplarListe) {
		this.kitaplarListe = kitaplarListe;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Düzenlendi");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}