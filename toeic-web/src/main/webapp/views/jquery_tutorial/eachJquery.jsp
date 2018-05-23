<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
    <style>

    </style>
</head>
<body>
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <h1>each</h1>
        <%--<script type="text/javascript">--%>
        <%--try{ace.settings.check('breadcrumbs','fixd')}catch (e) {--%>
        <%--}--%>
        <%--</script>--%>
        <%--<ul class="breadcrumb">--%>
        <%--<li>--%>
        <%--<i class="ace-icon fa fa-home home-icon"></i>--%>
        <%--<a href="#"><fmt:message key="label.home" bundle="${lang}"/></a>--%>
        <%--</li>--%>
        <%--<li class="active"><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/> </li>--%>
        <%--</ul>--%>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">

                </div>
            </div>
        </div>
    </div>
</div>

<script>
  //  var arr = [2,3,3,4,5,6];
  var temp=[];
  <c:if test="${not empty listDemo}">
    <c:forEach var="item" items="${listDemo}">
        temp.push('$(item.name)');
    </c:forEach>
  </c:if>
    $(document).ready(function(){
        // $.each(arr, function(index,value){
        //     console.log("value :" + value+ "- position: " +index);
        // });

        $.each(temp,function(index,name){
            console.log("name :" + name+ "- possion: " +index);
        });
    });
</script>
</body>
</html>
