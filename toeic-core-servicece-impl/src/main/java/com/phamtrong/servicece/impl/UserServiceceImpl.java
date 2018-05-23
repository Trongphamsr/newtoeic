package com.phamtrong.servicece.impl;

import com.phamtrong.core.dao.UserDao;
import com.phamtrong.core.dao.impl.UserDaoImpl;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.persistence.entity.UserEntity;
import com.phamtrong.core.servicece.UserServicece;
import com.phamtrong.core.utils.UserBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    UserDao userDao = new UserDaoImpl();
    public UserDTO isUserExist(UserDTO dto) {
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(),dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleByUser(UserDTO dto) {
        UserEntity entity = userDao.findUserByUsernameAndPassword(dto.getName(),dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = userDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();

        for(UserEntity item:(List<UserEntity>) objects[1]){
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOs.add(userDTO);
        }
        objects[1]=userDTOs;
        return objects;
    }
}
