package com.phamtrong.controller.admin;

import com.phamtrong.command.ListenGuidelineCommand;
import com.phamtrong.core.common.ultils.UploadUtil;
import com.phamtrong.core.dto.ListenGuidelineDTO;
import com.phamtrong.core.servicece.ListenGuidelineServicece;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.FormUtil;
import com.phamtrong.core.web.utils.RequestUtil;
import com.phamtrong.servicece.impl.ListenGuidelineServiceceImpl;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html","/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private transient final Logger log = Logger.getLogger(this.getClass());
    private ListenGuidelineServicece guidelineServicece = new ListenGuidelineServiceceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class,request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        HttpSession session = request.getSession();
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

        if(session!=null){
            request.setAttribute(WebConstant.ALERT,session.getAttribute(WebConstant.ALERT));
            request.setAttribute(WebConstant.MESSAGE_RESPONSE,session.getAttribute(WebConstant.MESSAGE_RESPONSE));

        }
        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
        request.setAttribute(WebConstant.MESSAGE_RESPONSE,resourceBundle.getString("label.guideline.listen.add.success"));
//        request.setAttribute(WebConstant.MESSAGE_RESPONSE,"add listenguideline success");
        if(command.getUrlType()!=null && command.getUrlType().equals(WebConstant.URL_LIST)){
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd =  request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            rd.forward(request,response);
        }else if(command.getUrlType()!=null && command.getUrlType().equals(WebConstant.URL_EDIT)){
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd =  request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            rd.forward(request,response);
        }
        session.removeAttribute(WebConstant.ALERT);
        session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = new ListenGuidelineCommand();
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
        HttpSession session = request.getSession();
        Set<String> valueTile = buildSetValueListenGuideline();
        try {
            Object[] objects= uploadUtil.wrireOrUpdateFile(request,valueTile,WebConstant.LISTENGUIDELINE);
            Map<String,String> mapValue = (Map<String, String>) objects[3];
            command=returnValueListenGuidelineCommand(valueTile,command, mapValue);
//            request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
//            request.setAttribute(WebConstant.MESSAGE_RESPONSE,bundle.getString("label.guideline.listen.add.success"));

        session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
        session.setAttribute(WebConstant.MESSAGE_RESPONSE,bundle.getString("label.guideline.listen.add.success"));
        }catch (FileUploadException e){
            log.error(e.getMessage(),e);
            session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE,bundle.getString("label.error"));
        }catch (Exception e){
            log.error(e.getMessage(),e);
            session.setAttribute(WebConstant.ALERT,WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE,bundle.getString("label.error"));
        }
        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list");
    }

    private ListenGuidelineCommand returnValueListenGuidelineCommand(Set<String> valueTile, ListenGuidelineCommand command, Map<String, String> mapValue) {
        for (String item:valueTile){
            if(mapValue.containsKey(item)){
                if(item.equals("pojo.title")){
                    command.getPojo().setTitle(mapValue.get(item));
                }else if(item.equals("pojo.content")){
                    command.getPojo().setContent(mapValue.get(item));
                }
            }
        }
        return command;
    }

    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue= new HashSet<String>();
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        return returnValue;
    }
}
