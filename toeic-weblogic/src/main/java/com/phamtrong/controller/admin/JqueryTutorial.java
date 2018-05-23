package com.phamtrong.controller.admin;

import com.phamtrong.command.JqueryCommand;
import com.phamtrong.core.dto.JqueryDTO;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-closest-method.html", "/admin-find-method.html","/admin-each-method.html"})
public class JqueryTutorial extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JqueryCommand command = FormUtil.populate(JqueryCommand.class,request);
        if(command!=null){
            if(command.getUrlType().equals(WebConstant.URL_CLOSEST_METHOD)){
                RequestDispatcher rd =  request.getRequestDispatcher("/views/jquery_tutorial/closestMethod.jsp");
                rd.forward(request,response);
            }

            if(command.getUrlType().equals(WebConstant.URL_FIND_METHOD)){
                RequestDispatcher rd =  request.getRequestDispatcher("/views/jquery_tutorial/findJquery.jsp");
                rd.forward(request,response);
            }

            if(command.getUrlType().equals(WebConstant.URL_EACH_METHOD)){
                List<JqueryDTO> jqueryDTOS = new ArrayList<JqueryDTO>();
                JqueryDTO jqueryDTO1= new JqueryDTO();
                jqueryDTO1.setName("myclass");
                jqueryDTO1.setAddress("hanoi");
                JqueryDTO jqueryDTO2 = new JqueryDTO();
                jqueryDTOS.add(jqueryDTO1);
                jqueryDTO2.setName("jsp_servlet");
                jqueryDTO2.setAddress("hai duong");
                jqueryDTOS.add(jqueryDTO2);
                request.setAttribute("listDemo", jqueryDTOS);
                RequestDispatcher rd =  request.getRequestDispatcher("/views/jquery_tutorial/eachJquery.jsp");
                rd.forward(request,response);
            }
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
