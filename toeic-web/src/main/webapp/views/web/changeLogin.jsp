<%@ include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="changepassword" value="/login-show-change.html"/>
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
                <fmt:message key="label.test.change" bundle="${lang}"/>
            </h4>

            <div class="space-6"></div>

            <form action="${changepassword}" method="post">
                <fieldset>
                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="password" name="pojo.oldpassword" class="form-control" placeholder="old password" />
                            <i class="ace-icon fa fa-lock"></i>
                        </span>
                    </label>

                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input type="password" name="pojo.newpassword" class="form-control" placeholder="new Password" />
                            <i class="ace-icon fa fa-lock"></i>
                        </span>
                    </label>
                    <label class="block clearfix">
                        <span class="block input-icon input-icon-right">
                            <input  type="password" name="pojo.confixpassword" class="form-control" placeholder="confix Password" />
                            <i class="ace-icon fa fa-lock"></i>
                        </span>
                    </label>
                    <div class="space"></div>
                    <div class="clearfix">
                        <button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
                            <i class="ace-icon fa fa-key"></i>
                            <span class="bigger-110">Change</span>
                        </button>
                    </div>
                    <div class="space-4"></div>
                </fieldset>
            </form>
        </div>
    </div>
</div>

</body>
</html>
