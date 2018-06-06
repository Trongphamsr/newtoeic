package com.phamtrong.core.servicece;

import com.phamtrong.core.dto.ListenGuidelineDTO;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Map;

public interface ListenGuidelineServicece {
    Object[] findListenGuidelineByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ListenGuidelineDTO findByListenGuidelineId(String property,Integer listenGuidelineId);
    void saveListenGuideline(ListenGuidelineDTO dto) throws ConstraintViolationException;
    ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto);
    Integer delete(List<Integer> ids);
}
