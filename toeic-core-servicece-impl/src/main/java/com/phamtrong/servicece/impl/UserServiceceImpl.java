package com.phamtrong.servicece.impl;

import com.phamtrong.core.dao.UserDao;
import com.phamtrong.core.dao.impl.UserDaoImpl;
import com.phamtrong.core.dto.CheckLogin;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.dto.UserImportDTO;
import com.phamtrong.core.persistence.entity.RoleEntity;
import com.phamtrong.core.persistence.entity.UserEntity;
import com.phamtrong.core.servicece.UserServicece;
import com.phamtrong.core.utils.UserBeanUtil;
import com.phamtrong.servicece.utils.SingletonDaoUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.*;

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

//    UserDao userDao = new UserDaoImpl();, thay the ,(userDao) listenGuidelineDaoImpl.getUserDaoInstance



//    public UserDTO isUserExist(UserDTO dto) {
//        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findUserByUsernameAndPassword(dto.getName(),dto.getPassword());
//        return UserBeanUtil.entity2Dto(entity);
//    }
//
//    public UserDTO findRoleByUser(UserDTO dto) {
//        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findUserByUsernameAndPassword(dto.getName(),dto.getPassword());
//        return UserBeanUtil.entity2Dto(entity);
//    }


    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit);
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();

        for(UserEntity item:(List<UserEntity>) objects[1]){
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOs.add(userDTO);
        }
        objects[1]=userDTOs;
        return objects;
    }

    public UserDTO findById(Integer userId) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(userId);
        UserDTO dto = UserBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveUser(UserDTO userDTO) {
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createdDate);
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        entity=SingletonDaoUtil.getUserDaoInstance().update(entity);
        userDTO= UserBeanUtil.entity2Dto(entity);
        return userDTO;
    }

    public CheckLogin checkLogin(String name, String password) {
       CheckLogin checkLogin = new CheckLogin();
        if(name!=null &&  password!=null){
            Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(name,password);
            checkLogin.setUserExist((Boolean) objects[0]);
            if(checkLogin.isUserExist()){
                checkLogin.setRoleName(objects[1].toString());
            }
        }
        return checkLogin;
    }

    public void validateImportUser(List<UserImportDTO> userImportDTOS) {
        List<String> names= new ArrayList<String>();
        List<String> roles = new ArrayList<String>();
        for (UserImportDTO item : userImportDTOS){
            if(item.isValid()){
                names.add((item.getUserName()));
                if(!roles.contains(item.getRoleName())){
                    roles.add(item.getRoleName());
                }
            }
        }
        Map<String , UserEntity> userEntityMap = new HashMap<String, UserEntity>();
        Map<String,RoleEntity> roleEntityMap = new HashMap<String, RoleEntity>();
        if(names.size()>0){
            List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoInstance().findByUsers(names);
            for(UserEntity item: userEntities){
                userEntityMap.put(item.getName().toUpperCase(), item);
            }
        }
        if(roles.size()>0){
            List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoInstance().findByRoles(roles);
            for(RoleEntity item :roleEntities){
                roleEntityMap.put(item.getName().toUpperCase(),item);
            }
        }
        for(UserImportDTO item:userImportDTOS) {
            String message=item.getError();
            if(item.isValid()){
                UserEntity userEntity = userEntityMap.get(item.getUserName().toUpperCase());
                if(userEntity!=null){
                    message += "<br/>";
                    message += "ten dang nhap da ton tai";
                }
                RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
                if(roleEntity==null){
                    message += "<br/>";
                    message += "vai tro khong ton tai";
                }
                if(StringUtils.isNotBlank(message)){
                    item.setValid(false);
                    item.setError(message.substring(5));
                }

            }
        }
    }

    public void saveUserImport(List<UserImportDTO> userImportDTOS) {
        for(UserImportDTO item: userImportDTOS ){
            if(item.isValid()){
                UserEntity userEntity = new UserEntity();
                userEntity.setName(item.getUserName());
                userEntity.setFullName(item.getFullName());
                userEntity.setPassword(item.getPassword());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                userEntity.setCreatedDate(timestamp);
                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoInstance().findEqualUnique("name",item.getRoleName().toUpperCase());
                userEntity.setRoleEntity(roleEntity);
                SingletonDaoUtil.getUserDaoInstance().save(userEntity);
            }
        }
    }
}
