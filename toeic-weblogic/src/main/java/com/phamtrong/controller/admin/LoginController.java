package com.phamtrong.controller.admin;

import com.phamtrong.command.UserCommand;
import com.phamtrong.core.dto.CheckLogin;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.FormUtil;
import com.phamtrong.core.web.utils.SingletonServiceUtil;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/login.html","/logout.html", "/login-change.html","/login-show-change.html"})
public class LoginController extends HttpServlet {
    private  final Logger log = Logger.getLogger(this.getClass());
    private final String CHANGE_LOGIN = "change_login";
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        if (command.getUrlType() != null && command.getUrlType().equals(CHANGE_LOGIN)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/changeLogin.jsp");
            rd.forward(request, response);
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String test =null;
////    null k co gia tri, co loi    neu  String test = "" k loi
//        try {
//            if(test.equals("admin")){
//                System.out.println("pass");
//            }
//
//        }catch (NullPointerException e){
//            log.error(e.getMessage(),e);
//        }
////        log.error("jsp error");

//        String name= request.getParameter("name");
//        String password= request.getParameter("password");

        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo= command.getPojo();
//        UserServicece userService = new UserServiceceImpl();

        if(pojo!=null) {
            CheckLogin login = SingletonServiceUtil.getUserDaoInstance().checkLogin(pojo.getName(), pojo.getPassword());
            if (login.isUserExist()) {
                if (login.getRoleName().equals(WebConstant.ROLE_ADMIN)) {
                    response.sendRedirect("/admin-home.html");
                } else if (login.getRoleName().equals(WebConstant.ROLE_USER)) {
                    response.sendRedirect("/home.html");
                }
            }else {
                request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
                request.setAttribute(WebConstant.MESSAGE_RESPONSE, bundle.getString("label.name.password.wrong"));
                RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
                rd.forward(request, response);
            }
        }

//        try {
//
//            if(SingletonServiceUtil.getUserDaoInstance().isUserExist(pojo) !=null){
//
//                if(SingletonServiceUtil.getUserDaoInstance().findRoleByUser(pojo)!=null && SingletonServiceUtil.getUserDaoInstance().findRoleByUser(pojo).getRoleDTO()!=null){
//                    if(SingletonServiceUtil.getUserDaoInstance().findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)){
//
//                        response.sendRedirect("/admin-home.html");
////                        request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
////                        request.setAttribute(WebConstant.MESSAGE_RESPONSE,"admin");
//                    }else if(SingletonServiceUtil.getUserDaoInstance().findRoleByUser(pojo).getRoleDTO().getName().equals(WebConstant.ROLE_USER)){
//                        response.sendRedirect("/home.html");
////                        request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
////                        request.setAttribute(WebConstant.MESSAGE_RESPONSE,"user");
//                    }
//                }
//
//            }
//
//        }catch (NullPointerException e){
//            log.error(e.getMessage(), e);
//            request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
//            request.setAttribute(WebConstant.MESSAGE_RESPONSE,"ten hoac mat khau sai");
//            RequestDispatcher rd =  request.getRequestDispatcher("/views/web/login.jsp");
//            rd.forward(request,response);
//        }
//
    }
}
