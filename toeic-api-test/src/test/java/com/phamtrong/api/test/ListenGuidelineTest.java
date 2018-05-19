package com.phamtrong.api.test;


import com.phamtrong.core.dao.ListenGuidelineDao;
import com.phamtrong.core.dao.impl.ListenGuidelineDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ListenGuidelineTest {

    ListenGuidelineDao listenGuidelineDao;
    @BeforeTest
    public void initData(){
        listenGuidelineDao = new ListenGuidelineDaoImpl();
    }


    @Test
    public void testFindByProperties(){
        //ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
       // Object[] result = listenGuidelineDao.findByProperty(null,null, null, null , 0, 2);

    }
    @Test
    public void  checkFindByProperty(){
        Map<String,Object> property = new HashMap<String, Object>();
        property.put("title","bai hd 8");
        property.put("content","noi dung hd 8");
        Object[] objects = listenGuidelineDao.findByProperty(property,null,null,null,null);
    }
}
