package com.phamtrong.servicece.impl;

import com.phamtrong.core.dao.RoleDao;
import com.phamtrong.core.dao.impl.RoleDaoImpl;
import com.phamtrong.core.dto.RoleDTO;
import com.phamtrong.core.persistence.entity.RoleEntity;
import com.phamtrong.core.servicece.RoleServicee;
import com.phamtrong.core.utils.RoleBeanUtil;
import com.phamtrong.servicece.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceeImpl implements RoleServicee {
//    RoleDao roleDao = new RoleDaoImpl();
    public List<RoleDTO> findAll(){
        List<RoleEntity> entities= SingletonDaoUtil.getRoleDaoInstance().fillAll();
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        for(RoleEntity item:entities){
            RoleDTO dto = RoleBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }
}
