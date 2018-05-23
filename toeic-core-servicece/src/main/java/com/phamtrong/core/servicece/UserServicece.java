package com.phamtrong.core.servicece;

import com.phamtrong.core.dto.UserDTO;

import java.util.Map;

public interface UserServicece {
    UserDTO isUserExist(UserDTO dto);
    UserDTO findRoleByUser(UserDTO dto);
    Object[] findByProperty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
