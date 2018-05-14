<%--
  Created by IntelliJ IDEA.
  User: PHAMTRONG
  Date: 5/14/2018
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title><dec:title default="Admin Page"/></title>
    <dec:head>
        <%--chua file js--%>
    </dec:head>
</head>
<body>
        <%@ include file="/common/admin/header.jsp"%>
        <%@ include file="/common/admin/menu.jsp"%>
        <dec:body/>
        <%@ include file="/common/admin/footer.jsp"%>
</body>
</html>
