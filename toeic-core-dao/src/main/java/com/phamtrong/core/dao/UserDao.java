package com.phamtrong.core.dao;

import com.phamtrong.core.data.dao.GenericDao;
import com.phamtrong.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
//    UserEntity isUserExist(String name, String password);
//    UserEntity findRoleByUser(String name, String password);

//    UserEntity findUserByUsernameAndPassword(String name, String password);

//    viet login
    Object[] checkLogin(String name,String password);
    List<UserEntity> findByUsers(List<String> names);
}
