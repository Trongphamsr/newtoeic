package com.phamtrong.controller.admin;

import com.phamtrong.command.UserCommand;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.servicece.UserServicece;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.FormUtil;
import com.phamtrong.servicece.impl.UserServiceceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin-user-list.html"})
public class UserController extends HttpServlet {
    UserServicece userServicece = new UserServiceceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserCommand command = FormUtil.populate(UserCommand.class,request);
        UserDTO pojo = command.getPojo();
        if(command.getUrlType().equals(WebConstant.URL_LIST)){

            Map<String, Object>  mapProperty = new HashMap<String, Object>();
            Object[] objects = userServicece.findByProperty(mapProperty, command.getSortExpression(),command.getSortDirection(),command.getFirstItem(), command.getMaxPageItems());
            //command.setMaxPageItems(5);
            command.setListResult((List<UserDTO>) objects[1]);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd =  request.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
