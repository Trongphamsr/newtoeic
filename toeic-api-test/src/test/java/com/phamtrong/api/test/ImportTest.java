package com.phamtrong.api.test;

import com.phamtrong.core.dao.RoleDao;
import com.phamtrong.core.dao.impl.RoleDaoImpl;
import com.phamtrong.core.persistence.entity.RoleEntity;
import org.testng.annotations.Test;

public class ImportTest {

    @Test
    public void TestImport(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = roleDao.findEqualUnique("name","USER_1");
        System.out.println(entity.getName());
    }
}
