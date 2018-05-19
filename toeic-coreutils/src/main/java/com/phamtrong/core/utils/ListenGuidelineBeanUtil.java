package com.phamtrong.core.utils;

import com.phamtrong.core.dto.ListenGuidelineDTO;
import com.phamtrong.core.persistence.entity.ListenguidelineEntity;

public class ListenGuidelineBeanUtil {

    public static ListenGuidelineDTO entity2Dto(ListenguidelineEntity entity){
        ListenGuidelineDTO dto = new ListenGuidelineDTO();
        dto.setListenGuidelineId(entity.getListenGuidelineId());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    public static ListenguidelineEntity dto2Entity(ListenGuidelineDTO dto){
       ListenguidelineEntity entity = new ListenguidelineEntity();
        entity.setListenGuidelineId(dto.getListenGuidelineId());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }

}
