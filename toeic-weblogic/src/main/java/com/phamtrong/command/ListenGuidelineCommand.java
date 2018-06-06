package com.phamtrong.command;

import com.phamtrong.core.dto.ListenGuidelineDTO;
import com.phamtrong.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {

    public ListenGuidelineCommand(){

        this.pojo= new ListenGuidelineDTO();
    }
}
