package com.phamtrong.core.servicece;

import com.phamtrong.core.dto.CheckLogin;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.dto.UserImportDTO;

import java.util.List;
import java.util.Map;

public interface UserServicece {
//    UserDTO isUserExist(UserDTO dto);
//    UserDTO findRoleByUser(UserDTO dto);

    Object[] findByProperty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userId);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    CheckLogin checkLogin(String name, String password);
    void validateImportUser(List<UserImportDTO> userImportDTOS);
}
