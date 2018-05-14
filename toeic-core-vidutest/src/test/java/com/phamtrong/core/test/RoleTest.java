package com.phamtrong.core.test;
import com.phamtrong.core.dao.RoleDao;
import com.phamtrong.core.dao.impl.RoleDaoImpl;
import com.phamtrong.core.persistence.entity.RoleEntity;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {

    @Test
    public void checkFindAll(){
//        interface k the khoi tao doi tuong dk, phai khoi tao theo class impliment cua no
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list= roleDao.fillAll();
    }

    @Test
    public void checkUpdateRole(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(2);
        entity.setName("USER_1");
        roleDao.update(entity);
    }

    @Test
    public void checkSaveRole(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(3);
        entity.setName("MANAGER");
        roleDao.update(entity);
    }

    @Test
    public void checkFindById(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = roleDao.findById(1);
    }

    @Test
    public void checkFindByProperty(){
        RoleDao roleDao = new RoleDaoImpl();
        String property = null;
        Object value=null;
        String sortExpression= null;
        String sortDirection= null;

        Object[] objects = roleDao.findByProperty(property,value,sortExpression,sortDirection);

    }
    @Test   public void checkDelete(){
        List<Integer> listId = new ArrayList<Integer>();
        listId.add(1);
        listId.add(2);
        RoleDao roleDao = new RoleDaoImpl();
        Integer count = roleDao.delete(listId);
    }
}
