package com.kutuphane.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.kutuphane.base.BaseService;
import com.kutuphane.base.DbException;
import com.kutuphane.base.IBaseService;
import com.kutuphane.base.Sort;
import com.kutuphane.base.THibernateUtil;
import com.kutuphane.entity.Kullanici;
import com.kutuphane.bean.KitaplarPage;

public class KullaniciService implements IBaseService<Kullanici>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1624126587315279484L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Kullanici> getAll() {
		BaseService baseService =new BaseService();
		return (List<Kullanici>) baseService.getAll(Kullanici.class);
		
	}

	@Override
	public Kullanici getById(Long id) {
		return (Kullanici) new BaseService().getById(Kullanici.class, id);
	}

	public Kullanici getByUsername(String username) {
		Session session = THibernateUtil.getSessionFactory().openSession();		
		 Criteria criteria = session.createCriteria(Kullanici.class);
		 criteria.add(Restrictions.eq("username", username));
		 return (Kullanici) criteria.uniqueResult();
	}
	
	@Override
	public Kullanici save(Kullanici obj) throws DbException {
        if(obj==null)
            throw new DbException("Kullanýcý Adý Boþ Olmamalýdýr");
        if(obj.getUsername()==null || obj.getUsername().equals(""))
            throw new DbException("Kullanýcý Adý Boþ Olmamalýdýr");
        if(obj.getEmail()==null || obj.getEmail().equals(""))
            throw new DbException("Kullanýcý Email Boþ Olmamalýdýr");
        
        return (Kullanici) new BaseService().save(obj);
	}

	@Override
	public Boolean delete(Kullanici obj) throws DbException {
		return  new BaseService().delete(obj);
	}

	@Override
	public Kullanici update(Kullanici obj) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Long id) {
		
		Kullanici silinecekKullanici = getById(id);
		try {
			delete(silinecekKullanici);
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
