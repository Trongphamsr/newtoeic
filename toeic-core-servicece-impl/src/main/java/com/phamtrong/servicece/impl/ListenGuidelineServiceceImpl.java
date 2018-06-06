package com.phamtrong.servicece.impl;

import com.phamtrong.core.dao.ListenGuidelineDao;
import com.phamtrong.core.dao.impl.ListenGuidelineDaoImpl;
import com.phamtrong.core.dto.ListenGuidelineDTO;
import com.phamtrong.core.persistence.entity.ListenguidelineEntity;
import com.phamtrong.core.servicece.ListenGuidelineServicece;
import com.phamtrong.core.utils.ListenGuidelineBeanUtil;
import com.phamtrong.servicece.utils.SingletonDaoUtil;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceceImpl implements ListenGuidelineServicece {
    private ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
    public Object[] findListenGuidelineByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ListenGuidelineDTO> result = new ArrayList<ListenGuidelineDTO>();
        Object[] objects =SingletonDaoUtil.getListenGuidelineDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit);
        for(ListenguidelineEntity item : (List<ListenguidelineEntity>)objects[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1]=result;
            return objects;
        }

    public ListenGuidelineDTO findByListenGuidelineId(String property,Integer listenGuidelineId) {
        ListenguidelineEntity entity = SingletonDaoUtil.getListenGuidelineDaoInstance().findEqualUnique(property,listenGuidelineId);
        ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    public void saveListenGuideline(ListenGuidelineDTO dto) throws ConstraintViolationException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(timestamp);
        ListenguidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getListenGuidelineDaoInstance().save(entity);
    }

    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setModifiedDate(timestamp);
        ListenguidelineEntity entity=ListenGuidelineBeanUtil.dto2Entity(dto);
        entity =SingletonDaoUtil.getListenGuidelineDaoInstance().update(entity);
        dto=ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    public Integer delete(List<Integer> ids) {
        Integer result= SingletonDaoUtil.getListenGuidelineDaoInstance().delete(ids);
        return result;
    }
}
