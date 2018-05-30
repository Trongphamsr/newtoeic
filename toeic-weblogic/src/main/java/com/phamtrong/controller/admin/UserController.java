package com.phamtrong.controller.admin;
import com.phamtrong.command.UserCommand;
import com.phamtrong.core.common.ultils.ExcelPoiUtil;
import com.phamtrong.core.common.ultils.SessionUtil;
import com.phamtrong.core.common.ultils.UploadUtil;
import com.phamtrong.core.dto.RoleDTO;
import com.phamtrong.core.dto.UserDTO;
import com.phamtrong.core.dto.UserImportDTO;
import com.phamtrong.core.servicece.RoleServicee;
import com.phamtrong.core.servicece.UserServicece;
import com.phamtrong.core.web.common.WebConstant;
import com.phamtrong.core.web.utils.FormUtil;
import com.phamtrong.core.web.utils.SingletonServiceUtil;
import com.phamtrong.core.web.utils.WebCommonUtil;
import com.phamtrong.servicece.impl.RoleServiceeImpl;
import com.phamtrong.servicece.impl.UserServiceceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-user-list.html","/ajax-admin-user-edit.html","/admin-user-import.html",
        "/admin-user-import-validate.html"})
public class UserController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    //    UserServicece userServicece = new UserServiceceImpl();
//    RoleServicee roleServicee = new RoleServiceeImpl();
    private final String SHOW_IMPORT_USER = "show_import_user";
    private final String READ_EXCEL = "read_excel";
    private final String VALIDATE_IMPORT = "validate_import";
    private final String LIST_USER_IMPORT = "list_user_import";
    ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> mapProperty = new HashMap<String, Object>();
            Object[] objects = SingletonServiceUtil.getUserDaoInstance().findByProperty(mapProperty, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            //command.setMaxPageItems(5);
            command.setListResult((List<UserDTO>) objects[1]);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            request.setAttribute(WebConstant.LIST_ITEMS, command);
//            if(command.getCrudaction()!=null && command.getCrudaction().equals("insert_success")){
//                request.setAttribute(WebConstant.ALERT,WebConstant.TYPE_SUCCESS);
//                request.setAttribute(WebConstant.MESSAGE_RESPONSE, "insert success");
//            }
            if (command.getCrudaction() != null) {
                Map<String, String> mapMessage = buildMapRedirectMessage(bundle);
//                Map<String,String> mapMessage = new HashMap<String, String>();
//                mapMessage.put(WebConstant.REDIRECT_INSERT,bundle.getString("label.user.message.add.success"));
//                mapMessage.put(WebConstant.REDIRECT_UPDATE,bundle.getString("label.user.message.update.success"));
//                mapMessage.put(WebConstant.REDIRECT_DELETE,bundle.getString("label.user.message.delete.success"));
//                mapMessage.put(WebConstant.REDIRECT_ERROR,bundle.getString("label.message.error"));
                WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if (pojo != null && pojo.getUserId() != null) {
                command.setPojo(SingletonServiceUtil.getUserDaoInstance().findById(pojo.getUserId()));
            }
            command.setRoles(SingletonServiceUtil.getRoleDaoInstance().findAll());
            request.setAttribute(WebConstant.FORM_ITEM, command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(SHOW_IMPORT_USER)) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            rd.forward(request, response);
        } else if (command.getUrlType() != null && command.getUrlType().equals(VALIDATE_IMPORT)) {
            List<UserImportDTO> userImportDTOS= (List<UserImportDTO>) SessionUtil.getInstance().getValue(request,LIST_USER_IMPORT);
            command.setMaxPageItems(3);
            command.setUserImportDTOS(userImportDTOS);
            command.setTotalItems(userImportDTOS.size());
            request.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/importuser.jsp");
            rd.forward(request, response);
        }
    }

    private Map<String, String> buildMapRedirectMessage(ResourceBundle bundle) {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstant.REDIRECT_INSERT, bundle.getString("label.user.message.add.success"));
        mapMessage.put(WebConstant.REDIRECT_UPDATE, bundle.getString("label.user.message.update.success"));
        mapMessage.put(WebConstant.REDIRECT_DELETE, bundle.getString("label.user.message.delete.success"));
        mapMessage.put(WebConstant.REDIRECT_ERROR, bundle.getString("label.message.error"));
        return mapMessage;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UploadUtil uploadUtil = new UploadUtil();
        Set<String> value = new HashSet<String>();
        value.add("urlType");
        Object[] objects = uploadUtil.wrireOrUpdateFile(request, value, "excel");
        try {
            UserCommand command = FormUtil.populate(UserCommand.class, request);
            UserDTO pojo = command.getPojo();
            if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getCrudaction() != null && command.getCrudaction().equals(WebConstant.INSERT_UPDATE)) {
//                request.setAttribute(WebConstant.MESSAGE_RESPONSE,"insert_success");
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(command.getRoleId());
                    pojo.setRoleDTO(roleDTO);
                    if (pojo != null && pojo.getUserId() != null) {
                        SingletonServiceUtil.getUserDaoInstance().updateUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);
                    } else {
                        SingletonServiceUtil.getUserDaoInstance().saveUser(pojo);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
                rd.forward(request, response);
            }
            if (objects != null) {
                String urlType = null;
                Map<String, String> maValue = (Map<String, String>) objects[3];

                for (Map.Entry<String, String> item : maValue.entrySet()) {
                    if (item.getKey().equals("urlType")) {
                        urlType = item.getValue();
                    }
                }
                if (urlType != null && urlType.equals(READ_EXCEL)) {
                    String fileLocation = objects[1].toString();
                    String fileName = objects[2].toString();
                    List<UserImportDTO> excelValues = returnValueFromExcel(fileName, fileLocation);
                    validateData(excelValues);
                    SessionUtil.getInstance().putValue(request,LIST_USER_IMPORT,excelValues);
                    response.sendRedirect("/admin-user-import-validate.html?urlType=validate_import");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_ERROR);
        }
    }

    private void validateData(List<UserImportDTO> excelValues) {
        Set<String> stringSet = new HashSet<String>();
        for(UserImportDTO item : excelValues){
            validateRequireField(item);
            validateDuplicate(item,stringSet);
        }
    }

    private void validateDuplicate(UserImportDTO item, Set<String> stringSet) {
        String message=item.getError();
        if(!stringSet.contains(item.getUserName())){
            stringSet.add(item.getUserName());
        }else{
            if(item.isValid()){
                message +="<br/>";
                message +=bundle.getString("label.username.duplicate");
            }
        }
        if(StringUtils.isNotBlank(message)){
            item.setValid(false);
            item.setError(message);
        }
    }

    private void validateRequireField(UserImportDTO item) {
        String message = "";
        // name rong
        if(StringUtils.isBlank(item.getUserName())){
            message +="<br/>";
            message +=bundle.getString("label.username.notempty");
        }
        if(StringUtils.isBlank(item.getPassword())){
            message +="<br/>";
            message +=bundle.getString("label.password.notempty");
        }
        if(StringUtils.isBlank(item.getRoleName())){
            message +="<br/>";
            message +=bundle.getString("label.rolename.notempty");
        }
        if(StringUtils.isNotBlank(message)){
            item.setValid(false);
        }

        item.setError(message);
    }


    private List<UserImportDTO> returnValueFromExcel(String fileName, String fileLocation) throws IOException {
        //FileInputStream excelFile = new FileInputStream(new File(fileLocation));
        Workbook workbook = ExcelPoiUtil.getWorkBook(fileName, fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        List<UserImportDTO> excellValues = new ArrayList<UserImportDTO>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            //System.out.println(row.getCell(0)+" - "+row.getCell(1));
            UserImportDTO userImportDTO = readDataFromEcel(row);
            excellValues.add(userImportDTO);
        }
        return excellValues;
    }

    private UserImportDTO readDataFromEcel(Row row) {
        UserImportDTO userImportDTO = new UserImportDTO();
        userImportDTO.setUserName(ExcelPoiUtil.getCellValue(row.getCell(0)));
        userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
        userImportDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(2)));
        userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));
        return userImportDTO;
    }
}
