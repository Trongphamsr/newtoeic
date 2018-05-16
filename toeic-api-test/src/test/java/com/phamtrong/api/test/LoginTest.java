package com.phamtrong.api.test;

import com.phamtrong.core.dao.UserDao;
import com.phamtrong.core.dao.impl.UserDaoImpl;
import com.phamtrong.core.persistence.entity.UserEntity;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest {
    private transient final Logger log = Logger.getLogger(this.getClass());
    @Test
    public void chekIsUserExist(){
        UserDao userDao = new UserDaoImpl();
        String name="admin";
        String password="123456";
        UserEntity entity = userDao.isUserExist(name, password);

        if(entity!=null){
            log.error("login success");
        }else{
            log.error("login false");
        }

    }


    @Test
    public void chekFindRoleUser(){
        UserDao userDao = new UserDaoImpl();
        String name="admin";
        String password="123456";
        UserEntity entity = userDao.isUserExist(name, password);
        log.error(entity.getRoleEntity().getRoleId()+" - "+ entity.getRoleEntity().getName());
    }
}
