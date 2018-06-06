package com.phamtrong.core.web.utils;

import com.phamtrong.servicece.impl.ListenGuidelineServiceceImpl;
import com.phamtrong.servicece.impl.RoleServiceeImpl;
import com.phamtrong.servicece.impl.UserServiceceImpl;

public class SingletonServiceUtil {
    private static UserServiceceImpl userServiceceImpl = null;
    private static RoleServiceeImpl roleServiceceImpl = null;
    private static ListenGuidelineServiceceImpl listenGuidelineServiceceImpl = null;

    public static UserServiceceImpl getUserServiceInstance(){
        if(userServiceceImpl==null){
            userServiceceImpl= new UserServiceceImpl();
        }
        return userServiceceImpl;
    }

    public static RoleServiceeImpl getRoleServiceInstance(){
        if(roleServiceceImpl==null){
            roleServiceceImpl= new RoleServiceeImpl();
        }
        return roleServiceceImpl;
    }

    public static ListenGuidelineServiceceImpl getListenGuidelineServiceInstance(){
        if(listenGuidelineServiceceImpl==null){
            listenGuidelineServiceceImpl= new ListenGuidelineServiceceImpl();
        }
        return listenGuidelineServiceceImpl;
    }
}
