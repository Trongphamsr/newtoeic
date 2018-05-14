package com.phamtrong.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable, T> {
    // interface viet phuong thuc k co than ham, khac voi class
    // interface hien thi thuoc ting rieng cua con ma cha k co
    // vd lop ca biet boi(cha) nhung trong ca co ca sau(biet bo), ca chim biet bay, do la thuoc tinh rieng cua lop con interface casau
    // trong interface mac dinh la public nen k can viet public List<T> fillAll();
    List<T> fillAll();

    // dung void hoac T
    T update(T entity);
    void save(T entity);
    T findById (ID var1);
    // list->kieu liet
    // size->kieu int nv de lay ca 2 ta dung object
    Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection);

    Integer delete(List<ID> ids);
}
