<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="formUrl" value="/login.html"/>
<c:url var="changeLogin" value="/login-change.html">
    <c:param name="urlType" value="change_login"/>
</c:url>
<html>
<head>
    <title>login page</title>
</head>
<body>

<div id="login-box" class="login-box visible widget-box no-border">
    <div class="widget-body">
        <div class="widget-main">
            <h4 class="header blue lighter bigger">
                <i class="ace-icon fa fa-coffee green"></i>
                <fmt:message key="label.test" bundle="${lang}"/>
            </h4>

            <div class="space-6"></div>

            <form action="${formUrl}" method="post">
                <fieldset>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                <%-- messageResponse trong WebConstant.java--%>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="pojo.name" class="form-control" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
                    </label>

                    <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="myInput" type="password" name="pojo.password" class="form-control" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span><br/>
                                                        <input type="checkbox" onclick="myFunction()">Show Password
                                                        <a href="${changeLogin}">change password</a><br/><br/>
                                                        <input type="checkbox" onclick="">rememmber Password
                                                        <a href="">forget password</a>
                    </label>

                    <%--<label class="block clearfix">--%>
														<%--<span class="block input-icon input-icon-right">--%>
															<%--<input type="password" name="confirmPassword" class="form-control" placeholder="confirm Password" />--%>
															<%--<i class="ace-icon fa fa-lock"></i>--%>
														<%--</span>--%>
                    <%--</label>--%>

                    <div class="space"></div>
                    <div class="clearfix">
                        <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">Login</span>
                        </button>
                    </div>

                    <div class="space-4"></div>
                </fieldset>
            </form>
        </div><!-- /.widget-main -->
    </div><!-- /.widget-body -->
</div><!-- /.login-box -->
<script>
    function myFunction() {
        var x = document.getElementById("myInput");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
</script>
</body>
</html>
