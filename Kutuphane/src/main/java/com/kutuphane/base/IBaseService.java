/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kutuphane.base;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vektorel
 */
public interface IBaseService<T> extends Serializable {
    
    public List<T> getAll();
    
    public T getById(Long id);
    
    public T save(T obj) throws DbException;
    
    public Boolean delete(T obj) throws DbException;
    
    public T update(T obj) throws DbException;
    
}
