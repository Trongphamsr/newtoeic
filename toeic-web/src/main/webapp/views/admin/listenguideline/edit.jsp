<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/admin-guideline-listen-edit.html"/>
<html>
<head>
    <title><fmt:message key="label.guideline.listen.edit" bundle="${lang}"/> </title>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <style>
        .error{
            color: red;
        }
    </style>
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

                    <form action="${formUrl}" method="post" enctype="multipart/form-data" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.title" bundle="${lang}"/> </label>
                            <div class="col-sm-9">
                                <input type="text" name="pojo.title" id="title" value="${item.pojo.title}"/>
                            </div>
                        </div>
                        <br/><br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image" bundle="${lang}"/> </label>
                            <div class="col-sm-9">
                                <input type="file" name="file" id="uploadFileImage"/>
                            </div>
                        </div>
                        <br/><br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.grammarguideline.upload.image.view" bundle="${lang}"/> </label>
                            <div class="col-sm-9">
                                <c:if test="${not empty item.pojo.image}">
                                    <c:set var="image" value="/repository/${item.pojo.image}"/>
                                </c:if>
                                <img src="${image}" id="viewImage" width="150px" height="150px"/>
                            </div>
                        </div>
                        <br/><br/>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"><fmt:message key="label.guideline.content" bundle="${lang}"/> </label>
                        </div>

                        <div class="form-group">
                             <div class="col-sm-12">
                                <c:if test="${not empty item.pojo.content}">
                                    <c:set var="content" value="${item.pojo.content}"/>
                                </c:if>
                                <textarea name="pojo.content" cols="80" rows="10" id="ListenGuidelineContent">${content}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="submit" class="btn btn-white btn-warning btn-bold" value="<fmt:message key="label.done" bundle="${lang}"/>">
                            </div>
                        </div>

                        <c:if test="${not empty item.pojo.listenGuidelineId}">
                            <input type="hidden" name="pojo.listenGuidelineId" value="${item.pojo.listenGuidelineId}"/>
                        </c:if>

                    </form>

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

                    <%--<input type="checkbox" id="sex" onchange="changeValueCheckbox()">--%>
                    <%--<p id="textSex"></p>--%>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var listenGuidelineId='';
    <c:if test="${not empty item.pojo.listenGuidelineId}">
        listenGuidelineId=${item.pojo.listenGuidelineId}
    </c:if>

    $(document).ready(function(){
        // hideAllWhenClickButton();
       // CKEDITOR.replace('ListenGuidelineContent');
        var editor = CKEDITOR.replace( 'ListenGuidelineContent' );
        CKFinder.setupCKEditor( editor, '/ckfinder/' );
        $('#uploadFileImage').change(function(){
            readUrl(this,"viewImage");
        });
    });

     validateData();
     function validateData(){
         $("#formEdit").validate({
             ignore: [],
             rules: [],
             messages: []
         });

         $( "#title" ).rules( "add", {
             required: true,
             messages: {
                 // required: "khong de trong title"
                 required: '<fmt:message key="label.empty" bundle="${lang}"/>'
             }
         });

         if(listenGuidelineId==''){
             $( "#uploadFileImage" ).rules( "add", {
                 required: true,
                 messages: {
                     // required: "khong de trong title"
                     required: '<fmt:message key="label.empty" bundle="${lang}"/>'
                 }
             });
         }

         $( "#ListenGuidelineContent" ).rules( "add", {
             required: function(){
                 CKEDITOR.instances.listenGuidelineContent.updateElement();
             },
             messages: {
                 // required: "khong de trong title"
                 required: '<fmt:message key="label.empty" bundle="${lang}"/>'
             }
         });
     }

    function readUrl(input,imageId) {
         if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload= function (e) {
                $('#'+imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }l


<%----------------------------------------------------%>
    // function  hideAllWhenClickButton(){
    //     $("#btn-hide").click(function(){
    //        $(".text").hide();
    //     });
    // }
    // function changeValueCheckbox(){
    //     if($('#sex').prop('checked')==true){
    //         $('textSex').html('<h1>Male</h1>');
    //     }else{
    //         $('textSex').html('<h1>Female</h1>');
    //     }
    // }
</script>
</body>
</html>
