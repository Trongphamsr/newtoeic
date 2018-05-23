<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin-guideline-listen-edit.html"/>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/> </title>
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
                <li class="active"><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/></li>
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

                    <%--<form action="${formUrl}" method="post" enctype="multipart/form-data">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.title" bundle="${lang}"/> </label>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<input type="text" name="pojo.title"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<br/><br/>--%>

                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image" bundle="${lang}"/> </label>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<input type="file" name="file"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<br/><br/>--%>

                        <%--<div class="form-group">--%>
                            <%--<label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.content" bundle="${lang}"/> </label>--%>
                            <%--<div class="col-sm-9">--%>
                                <%--<input type="text" name="pojo.content"/>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<div class="col-sm-12">--%>
                                <%--<input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}"/>">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</form>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.title" bundle="${lang}"/> </label>--%>
                        <%--<div class="col-sm-9">--%>
                            <%--<h1>this is a heading</h1>--%>
                            <%--<P class="text">this is a paragrap</P>--%>
                            <%--<p>this in another paragrap</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.title" bundle="${lang}"/> </label>--%>
                        <%--<div class="col-sm-9">--%>
                            <%--<button onclick="hideAllWhenClickButton()" id="btn-hide">click</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <input type="checkbox" id="sex" onchange="changeValueCheckbox()">
                    <p id="textSex"></p>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        // hideAllWhenClickButton();
    });
    function  hideAllWhenClickButton(){
        $("#btn-hide").click(function(){
           $(".text").hide();
        });
    }
    function changeValueCheckbox(){
        if($('#sex').prop('checked')==true){
            $('textSex').html('<h1>Male</h1>');
        }else{
            $('textSex').html('<h1>Female</h1>');
        }
    }
</script>
</body>
</html>
