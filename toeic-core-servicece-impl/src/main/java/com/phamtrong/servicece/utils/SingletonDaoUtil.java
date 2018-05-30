package com.phamtrong.servicece.utils;

import com.phamtrong.core.dao.impl.ListenGuidelineDaoImpl;
import com.phamtrong.core.dao.impl.RoleDaoImpl;
import com.phamtrong.core.dao.impl.UserDaoImpl;

public class SingletonDaoUtil {
    private static UserDaoImpl userDaoImpl = null;
    private static RoleDaoImpl roleDaoImpl = null;
    private static ListenGuidelineDaoImpl listenGuidelineDaoImpl = null;

    public static UserDaoImpl getUserDaoInstance(){
        if(userDaoImpl==null){
            userDaoImpl= new UserDaoImpl();
        }
        return userDaoImpl;
    }

    public static RoleDaoImpl getRoleDaoInstance(){
        if(roleDaoImpl==null){
            roleDaoImpl= new RoleDaoImpl();
        }
        return roleDaoImpl;
    }

    public static ListenGuidelineDaoImpl getListenGuidelineDaoInstance(){
        if(listenGuidelineDaoImpl==null){
            listenGuidelineDaoImpl= new ListenGuidelineDaoImpl();
        }
        return listenGuidelineDaoImpl;
    }
}
