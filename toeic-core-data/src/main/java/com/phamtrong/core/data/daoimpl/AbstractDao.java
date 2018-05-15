package com.phamtrong.core.data.daoimpl;


import com.phamtrong.core.common.constant.CoreConstant;
import com.phamtrong.core.common.ultils.HibernateUtil;
import com.phamtrong.core.data.dao.GenericDao;
import org.apache.log4j.Logger;
import org.hibernate.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID,T> {

    private final Logger log = Logger.getLogger(this.getClass());
//    protected thang con cua no sd dk, nhung thang ngoai k sd dk
// T dai dien cho cac Class nhu RoleEntity, ....
    private Class<T> persistenceClass;

    public  AbstractDao(){
        this.persistenceClass= (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    public List<T> fillAll() {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction =session.beginTransaction();
        try {
            // hql
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list=query.list();
            transaction.commit();

        }catch (HibernateException e){
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw  e;
        }finally {
            session.close();
        }

        return list;
    }

    public T update(T entity) {
        // sung merge
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        try {
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();

        }catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(), e);
            throw  e;

        }finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        // mo ket noi
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // dung ham persist de luu
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }finally {
            session.close();
        }
    }

    public T findById(ID id) {
        T result = null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        try{
            result = (T) session.get(persistenceClass,id);
            if(result==null){
                throw  new ObjectNotFoundException("not found " +id, null);
            }

        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
// "roleid", "gia tri","thuoctinh","tanggiam, desc"
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Object totalItem=0;
        try {

            StringBuilder sql1 = new StringBuilder("from ");
            sql1.append(getPersistenceClassName());
            if(property!=null&& value !=null){
                sql1.append(" where ").append(property).append("= :value");
            }
            if(sortExpression!=null && sortDirection!=null){
                sql1.append(" order by ").append(sortExpression);
                sql1.append(" " +(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1 = session.createQuery(sql1.toString());
            // truyen truc tiep k can setParametter
           if(value!=null){
               query1.setParameter("value", value);
           }
            list=query1.list();
            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName());
            if(property!=null && value!=null){
                sql2.append(" where ").append(property).append("= :value");
            }
            Query query2 = session.createQuery(sql2.toString());
            if(value!=null){
                query2.setParameter("value", value);
            }
            totalItem = query2.list().get(0);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count =0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();

        try {
            // item doi tuong 1 hoac 2 hoac 3 con ids la 123
            for (ID item :ids){
                T t = (T) session.get(persistenceClass,item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        return count;
    }
}