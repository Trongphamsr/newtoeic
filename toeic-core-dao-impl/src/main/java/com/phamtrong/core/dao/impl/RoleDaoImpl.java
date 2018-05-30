package com.phamtrong.core.dao.impl;

import com.phamtrong.core.common.ultils.HibernateUtil;
import com.phamtrong.core.dao.RoleDao;
import com.phamtrong.core.data.daoimpl.AbstractDao;
import com.phamtrong.core.persistence.entity.RoleEntity;
import com.phamtrong.core.persistence.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {
    @Override
    public List<RoleEntity> findByRoles(List<String> roles) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
        try{
            StringBuilder sql = new StringBuilder(" FROM RoleEntity ue WHERE ue.name IN(:roles) ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("roles",roles);
            roleEntities=query.list();

        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return roleEntities;
    }
}
