<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><dec:title default="Admin Page" /></title>
    <link rel="stylesheet" href="<c:url value='/template/admin/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.2.0/css/font-awesome.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/fonts/fonts.googleapis.com.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/css/ace.min.css'/>" class="ace-main-stylesheet" id="main-ace-style"/>
    <script src="<c:url value='/template/admin/js/ace-extra.min.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/template/admin/vidu/style.css'/>"/>
    <dec:head />
</head>
<body class="no-skin">
        <%@ include file="/common/admin/header.jsp"%>
        <%--<%@ include file="/common/admin/menu.jsp"%>--%>
        <dec:body/>
        <%--<%@ include file="/common/admin/footer.jsp"%>--%>

        <script src="<c:url value='/template/admin/js/bootstrap.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/excanvas.min.js'/>"></script>
        <script src="<c:url value='/template/admin/js/jquery-ui.custom.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.ui.touch-punch.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.easypiechart.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.sparkline.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.flot.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.flot.pie.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/jquery.flot.resize.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/ace-elements.min.js' />"></script>
        <script src="<c:url value='/template/admin/js/ace.min.js' />"></script>
</body>
</html>
