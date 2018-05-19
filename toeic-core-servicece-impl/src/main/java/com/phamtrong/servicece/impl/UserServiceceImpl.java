package com.phamtrong.servicece.impl;

import com.phamtrong.core.dao.UserDao;
import com.phamtrong.core.dao.impl.UserDaoImpl;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.persistence.entity.UserEntity;
import com.phamtrong.core.servicece.UserServicece;
import com.phamtrong.core.utils.UserBeanUtil;

public class UserServiceceImpl implements UserServicece {
//    public UserDTO isUserExist(UserDTO dto) {
//        UserDao userDao = new UserDaoImpl();
//        UserEntity entity = userDao.isUserExist(dto.getName(),dto.getPassword());
//        return UserBeanUtil.entity2Dto(entity);
//    }
//
//    public UserDTO findRoleByUser(UserDTO dto) {
//        UserDao userDao = new UserDaoImpl();
//        UserEntity entity = userDao.findRoleByUser(dto.getName(),dto.getPassword());
//        return UserBeanUtil.entity2Dto(entity);
//    }

    public UserDTO isUserExist(UserDTO dto) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(),dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleByUser(UserDTO dto) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(),dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }
}
