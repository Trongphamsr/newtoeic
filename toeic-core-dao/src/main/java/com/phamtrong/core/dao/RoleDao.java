package com.phamtrong.core.dao;

import com.phamtrong.core.data.dao.GenericDao;
import com.phamtrong.core.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleDao extends GenericDao<Integer, RoleEntity> {
    List<RoleEntity> findByRoles(List<String> roles);

}