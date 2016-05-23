package com.kutuphane.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.kutuphane.base.Sort;
import com.kutuphane.base.THibernateUtil;
import com.kutuphane.bean.KitaplarPage;
import com.kutuphane.base.BaseService;
import com.kutuphane.base.DbException;
import com.kutuphane.base.IBaseService;
import com.kutuphane.entity.Kitaplar;
import com.kutuphane.entity.Kullanici;

public class KutuphaneService implements IBaseService<Kitaplar> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Kitaplar> getAll() {
		com.kutuphane.base.BaseService baseService = new BaseService();
		return (List<Kitaplar>) baseService.getAll(Kitaplar.class);
	}

	@Override
	public Kitaplar getById(Long id) {
		return null;
	}

	@Override
	public Kitaplar save(Kitaplar obj) throws DbException {
		
	        
	        return (Kitaplar) new BaseService().save(obj);
	}

	@Override
	public Boolean delete(Kitaplar obj) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kitaplar update(Kitaplar obj) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	
public void deleteById(Long id) {
		
		Kitaplar silinecekKitap = getById(id);
		try {
			delete(silinecekKitap);
		} catch (DbException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public KitaplarPage getByPaging(int first, int pageSize,Map<String, Object> filters,String sortField,Sort sort) {
		Session session = THibernateUtil.getSessionFactory().openSession();		
		 Criteria criteria = session.createCriteria(Kullanici.class);
		 
		 if(filters.get("id")!=null){
			 criteria.add(Restrictions.eq("id", Long.parseLong(filters.get("id").toString())));
		 }
		 
		 if(filters.get("username")!=null){
			 criteria.add(Restrictions.ilike("username", filters.get("username").toString(),MatchMode.ANYWHERE));
		 }
		 
		 if(filters.get("email")!=null){
			 criteria.add(Restrictions.ilike("email",filters.get("email").toString(),MatchMode.ANYWHERE));
		 }
		 
	
		 criteria.setProjection(Projections.rowCount());
		 int recordCount = Integer.parseInt(criteria.uniqueResult().toString());
		 criteria.setProjection(null);
		 
		 if(sortField!=null && !sortField.equals("")){
			 if(sort==Sort.ASC)
				 criteria.addOrder(Order.asc(sortField));
			 else
				 criteria.addOrder(Order.desc(sortField));	 
		 }
		 
		 criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 criteria.setFirstResult(first);
		 criteria.setMaxResults(pageSize);
		 return new KitaplarPage(criteria.list(), recordCount);		
	}

	
	
	
}
