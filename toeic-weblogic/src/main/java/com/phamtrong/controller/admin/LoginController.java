package com.phamtrong.controller.admin;

import com.phamtrong.command.UserCommand;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.web.utils.FormUtil;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login.html")
public class LoginController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd =  request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request,response);
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

        RequestDispatcher rd =  request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request,response);
    }

}
