package com.phamtrong.controller.admin;

import com.phamtrong.command.ListenGuidelineCommand;
import com.phamtrong.core.common.ultils.UploadUtil;
import com.phamtrong.core.dto.ListenGuidelineDTO;
import com.phamtrong.core.servicece.ListenGuidelineServicece;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.FormUtil;
import com.phamtrong.core.web.utils.RequestUtil;
import com.phamtrong.core.web.utils.SingletonServiceUtil;
import com.phamtrong.core.web.utils.WebCommonUtil;
import com.phamtrong.servicece.impl.ListenGuidelineServiceceImpl;
import com.phamtrong.servicece.utils.SingletonDaoUtil;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

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
    //private ListenGuidelineServicece guidelineServicece = new ListenGuidelineServiceceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class,request);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

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

        //command.setMaxPageItems(2);
//        HttpSession session = request.getSession();
//        request.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
//        request.setAttribute(WebConstant.MESSAGE_RESPONSE,resourceBundle.getString("label.guideline.listen.add.success"));
//        if(session!=null){
//            request.setAttribute(WebConstant.ALERT,session.getAttribute(WebConstant.ALERT));
//            request.setAttribute(WebConstant.MESSAGE_RESPONSE,session.getAttribute(WebConstant.MESSAGE_RESPONSE));
//        }
        if(command.getUrlType()!=null && command.getUrlType().equals(WebConstant.URL_LIST)){
            if(command.getCrudaction()!=null && command.getCrudaction().equals(WebConstant.REDIRECT_DELETE)){
                List<Integer> ids = new ArrayList<Integer>();
                for(String item:command.getCheckList()){
                    ids.add(Integer.parseInt(item));
                }
                Integer result = SingletonServiceUtil.getListenGuidelineServiceInstance().delete(ids);
                if(result!= ids.size()){
                    command.setCrudaction(WebConstant.REDIRECT_ERROR);
                }
            }
            executeSearchListenGuideline(request,command);
            if (command.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapRedirectMessage(resourceBundle);
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd =  request.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
            rd.forward(request,response);
        }else if(command.getUrlType()!=null && command.getUrlType().equals(WebConstant.URL_EDIT)){
            if(command.getPojo()!=null && command.getPojo().getListenGuidelineId()!=null){
                command.setPojo(SingletonServiceUtil.getListenGuidelineServiceInstance().findByListenGuidelineId("listenGuidelineId", command.getPojo().getListenGuidelineId()));
            }
           request.setAttribute(WebConstant.FORM_ITEM,command);
            RequestDispatcher rd =  request.getRequestDispatcher("/views/admin/listenguideline/edit.jsp");
            rd.forward(request,response);
        }
//        session.removeAttribute(WebConstant.ALERT);
//        session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
    }

    private Map<String,String> buildMapRedirectMessage(ResourceBundle resourceBundle) {

        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.listenguideline.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.listenguideline.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.listenguideline.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        return mapMessage;
    }

    private void executeSearchListenGuideline(HttpServletRequest request, ListenGuidelineCommand command) {
        Map<String ,Object> properties=buildMapProperties(command);
        RequestUtil.initSearchBean(request,command);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceInstance().findListenGuidelineByProperties(properties,command.getSortExpression(), command.getSortDirection(),command.getFirstItem(),command.getMaxPageItems());
        command.setListResult((List<ListenGuidelineDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String,Object> buildMapProperties(ListenGuidelineCommand command) {
        Map<String,Object> properties = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(command.getPojo().getTitle())){
            properties.put("title",command.getPojo().getTitle());
        }
        return properties;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListenGuidelineCommand command = new ListenGuidelineCommand();
        ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
        UploadUtil uploadUtil = new UploadUtil();
//        HttpSession session = request.getSession();
        Set<String> valueTile = buildSetValueListenGuideline();
        Object[] objects= uploadUtil.wrireOrUpdateFile(request,valueTile,WebConstant.LISTENGUIDELINE);
        boolean checkStatusUploadImage = (Boolean) objects[0];
        if(!checkStatusUploadImage){
            response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
        }else{
            ListenGuidelineDTO dto = command.getPojo();
            if(StringUtils.isNotBlank(objects[2].toString())){
                dto.setImage(objects[2].toString());
            }
            Map<String,String> mapValue= (Map<String, String>) objects[3];
            dto=returnValueOfDTO(dto,mapValue);
            if(dto!=null){
                if(dto.getListenGuidelineId()!=null){
                    ListenGuidelineDTO listenGuidelineDTO = SingletonServiceUtil.getListenGuidelineServiceInstance().findByListenGuidelineId("listenGuidelineId",dto.getListenGuidelineId());
                    if(dto.getImage()==null){
                        dto.setImage(listenGuidelineDTO.getImage());
                    }
                    dto.setCreatedDate(listenGuidelineDTO.getCreatedDate());
                    ListenGuidelineDTO result = SingletonServiceUtil.getListenGuidelineServiceInstance().updateListenGuideline(dto);
                    if(result!=null){
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_update");
                    }else{
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }else{
                    try{
                        SingletonServiceUtil.getListenGuidelineServiceInstance().saveListenGuideline(dto);
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_insert");
                    }catch (ConstraintViolationException e){
                        log.error(e.getMessage(), e);
                        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&&crudaction=redirect_error");
                    }
                }
            }
        }

        //Map<String,String> mapValue = (Map<String, String>) objects[3];
        //command=returnValueListenGuidelineCommand(valueTile,command, mapValue);
//        response.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list");
    }

    private ListenGuidelineDTO returnValueOfDTO(ListenGuidelineDTO dto,Map<String, String> mapValue) {
//        for (String item:valueTile){
//            if(mapValue.containsKey(item)){
//                if(item.equals("pojo.title")){
//                    command.getPojo().setTitle(mapValue.get(item));
//                }else if(item.equals("pojo.content")){
//                    command.getPojo().setContent(mapValue.get(item));
//                }
//            }
//        }
//        return command;
        for(Map.Entry<String,String> item:mapValue.entrySet()){
            if(item.getKey().equals("pojo.title")){
                dto.setTitle(item.getValue());
            }else if(item.getKey().equals("pojo.content")){
                dto.setContent(item.getValue());
            }else if(item.getKey().equals("pojo.listenGuidelineId")){
                dto.setListenGuidelineId(Integer.parseInt(item.getValue().toString()));
            }
        }
        return dto;
    }

    private Set<String> buildSetValueListenGuideline() {
        Set<String> returnValue= new HashSet<String>();
        returnValue.add("pojo.title");
        returnValue.add("pojo.content");
        returnValue.add("pojo.listenGuidelineId");
        return returnValue;
    }
}
