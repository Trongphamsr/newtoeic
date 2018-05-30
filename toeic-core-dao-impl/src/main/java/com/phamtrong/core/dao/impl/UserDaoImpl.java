package com.phamtrong.core.dao.impl;
import com.phamtrong.core.common.ultils.HibernateUtil;
import com.phamtrong.core.dao.UserDao;
import com.phamtrong.core.data.daoimpl.AbstractDao;
import com.phamtrong.core.persistence.entity.RoleEntity;
import com.phamtrong.core.persistence.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {
    @Override
    public Object[] checkLogin(String name, String password) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        boolean isUserExist=false;
        String roleName = null;

        try {
            StringBuilder sql = new StringBuilder(" FROM UserEntity ue WHERE ue.name= :name AND ue.password= :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("password", password);
            if(query.list().size()>0){
                isUserExist=true;
                UserEntity userEntity = (UserEntity) query.uniqueResult();
                roleName = userEntity.getRoleEntity().getName();
            }
        }catch (HibernateException e){
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return new Object[]{isUserExist,roleName};
    }

//    @Override
//    public UserEntity isUserExist(String name, String password) {
//        UserEntity entity = new UserEntity();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction =session.beginTransaction();
//
//        try {
//            StringBuilder sql = new StringBuilder("FROM UserEntity WHERE name= :name AND password= :password");
//            Query query = session.createQuery(sql.toString());
//            query.setParameter("name", name);
//            query.setParameter("password", password);
//            entity = (UserEntity) query.uniqueResult();
//            transaction.commit();
//        }catch (HibernateException e){
//            transaction.rollback();
//            throw e;
//        }finally {
//            session.close();
//        }
//        return entity;
//    }
//
//    @Override
//    public UserEntity findRoleByUser(String name, String password) {
//        UserEntity entity = new UserEntity();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction =session.beginTransaction();
//
//        try {
//            StringBuilder sql = new StringBuilder("FROM UserEntity WHERE name= :name AND password= :password");
//            Query query = session.createQuery(sql.toString());
//            query.setParameter("name", name);
//            query.setParameter("password", password);
//            entity = (UserEntity) query.uniqueResult();
//            transaction.commit();
//        }catch (HibernateException e){
//            transaction.rollback();
//            throw e;
//        }finally {
//            session.close();
//        }
//        return entity;
//    }

//    @Override
//    public UserEntity findUserByUsernameAndPassword(String name, String password) {
//        UserEntity entity = new UserEntity();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction =session.beginTransaction();
//
//        try {
//            StringBuilder sql = new StringBuilder("FROM UserEntity WHERE name= :name AND password= :password");
//            Query query = session.createQuery(sql.toString());
//            query.setParameter("name", name);
//            query.setParameter("password", password);
//            entity = (UserEntity) query.uniqueResult();
//            transaction.commit();
//        }catch (HibernateException e){
//            transaction.rollback();
//            throw e;
//        }finally {
//            session.close();
//        }
//        return entity;
//    }



}
