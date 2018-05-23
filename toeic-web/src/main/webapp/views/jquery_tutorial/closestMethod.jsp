<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .ancestors * {
            display: block;
            border: 2px solid lightgrey;
            color: lightgrey;
            padding: 5px;
            margin: 15px;
        }
    </style>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
</head>
<body>
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <h1>closest</h1>
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
                    <div class="ancestors">
                        <div style="width:500px">div (great-grandparent)
                            <ul>ul (second ancestor - second grandparent)
                                <ul>ul (first ancestor - first grandparent)
                                    <li>li (direct parent)
                                        <span>span</span>
                                    </li>
                                </ul>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>

<script>
$(document).ready(function(){
$("span").closest("ul").css({"color": "red", "border": "2px solid red"});
});
</script>
</body>
</html>
