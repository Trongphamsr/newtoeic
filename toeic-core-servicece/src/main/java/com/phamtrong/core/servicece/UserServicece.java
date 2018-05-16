package com.phamtrong.core.servicece;

import com.phamtrong.core.dto.UserDTO;

public interface UserServicece {
    UserDTO isUserExist(UserDTO dto);
    UserDTO findRoleByUser(UserDTO dto);
}
