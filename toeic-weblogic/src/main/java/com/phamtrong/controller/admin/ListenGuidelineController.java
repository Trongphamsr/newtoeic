package com.phamtrong.controller.admin;

import com.phamtrong.command.ListenGuidelineCommand;
import com.phamtrong.core.dto.ListenGuidelineDTO;
import com.phamtrong.core.servicece.ListenGuidelineServicece;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.RequestUtil;
import com.phamtrong.servicece.impl.ListenGuidelineServiceceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuidelineController extends HttpServlet {

    private ListenGuidelineServicece guidelineServicece = new ListenGuidelineServiceceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = new ListenGuidelineCommand();
//
//
//        List<ListenGuidelineDTO> listenGuidelineDTOS = new ArrayList<ListenGuidelineDTO>();
//        ListenGuidelineDTO dto1 = new ListenGuidelineDTO();
//        dto1.setTitle("bai huong dan nghe 1");
//        dto1.setContent("noi dung bai huong dan nghe 1");
//        listenGuidelineDTOS.add(dto1);
//
//        ListenGuidelineDTO dto2 = new ListenGuidelineDTO();
//        dto2.setTitle("bai huong dan nghe 2");
//        dto2.setContent("noi dung bai huong dan nghe 2");
//        listenGuidelineDTOS.add(dto2);
//
//        command.setListResult(listenGuidelineDTOS);
//        command.setMaxPageItems(1);
//        command.setTotalItems(listenGuidelineDTOS.size());
//        request.setAttribute(WebConstant.LIST_ITEMS, command);


//-------------------------------------------------------

//        command.setMaxPageItems(2);
//        RequestUtil.initSearchBean(request,command);
//        Object[] objects = guidelineServicece.findListenGuidelineByProperties(null,null,command.getSortExpression(), command.getSortDirection(),command.getFirstItem(),command.getMaxPageItems());
//        command.setListResult((List<ListenGuidelineDTO>) objects[1]);
//        command.setTotalItems(Integer.parseInt(objects[0].toString()));
//        request.setAttribute(WebConstant.LIST_ITEMS,command);
        RequestDispatcher rd =  request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
        rd.forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
