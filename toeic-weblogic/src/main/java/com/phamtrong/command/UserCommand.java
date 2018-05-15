package com.phamtrong.command;

import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand(){
        this.pojo= new UserDTO();
    }
}
