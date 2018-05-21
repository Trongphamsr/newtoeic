<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="requestUrl" value="/admin-guideline-listen-list.html"/>
<c:url value="/admin-guideline-listen-edit.html" var="listenGuidelineEditUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.list" bundle="${lang}"/> </title>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <script>
        $(document).ready(function(){
            $("div.alert").delay(3000).slideUp();
        });
    </script>
</head>
<body>

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>
                </li>
                <li class="active"><fmt:message key="label.guideline.listen.list" bundle="${lang}"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <a href="${listenGuidelineEditUrl}" type="button">them bai hd</a>
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                <%-- messageResponse trong WebConstant.java--%>
                                ${messageResponse}
                        </div>
                    </c:if>

                    <div class="table-responsive">
                        <fmt:bundle basename="ApplicationResources">
                            <%----%>
                            <display:table id="tableList" name="items.listResult" partialList="true" size="${items.totalItems}"
                                           pagesize="${items.maxPageItems}" sort="external"  requestURI="${requestUrl}" class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <display:column property="title" titleKey="label.guideline.listen.title" sortable="true" sortName="title"/>
                                <display:column property="content" titleKey="label.guideline.listen.content" sortable="true" sortName="content"/>
                            </display:table>
                        </fmt:bundle>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
