/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kutuphane.base;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author kutuphane
 */
public class BaseService  {

    
    public List getAll(Class cls) {
        Session session = THibernateUtil.getSessionFactory().openSession();
        Criteria crtr = session.createCriteria(cls);
        List l= crtr.list();
        return l;
    }

    public Object getById(Class cls,Long id) {
           Session session = THibernateUtil.getSessionFactory().openSession();
        Criteria crtr = session.createCriteria(cls);
        crtr.add(Restrictions.eq("id", id));
        //crtr.add(Restrictions.ilike("kitap.isbn", "234",MatchMode.ANYWHERE));
        Object o= crtr.uniqueResult();
        return o;
    }

    public Object save(Object obj) throws DbException {
        Session session = THibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        return obj;
    }

    public Boolean delete(Object obj) throws DbException {
        Session session = THibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(obj);
        transaction.commit();
        return true;
    }

    public Object update(Object obj) throws DbException {
        Session session = THibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
        return obj;
    }
}
