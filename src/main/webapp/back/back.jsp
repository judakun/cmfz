<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<html>
<head>
    <title>Title</title>

    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/boot/css/bootstrap.min.css">

    <%--引入jqgrid与bootstrap整合的css样式--%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

    <%--引入jquery的js文件--%>

    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>

    <%--引入bootStrap的js文件--%>

    <script src="${pageContext.request.contextPath}/statics/boot/js/bootstrap.min.js"></script>

    <%--引入jqgrid的js文件--%>

    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>

    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>

    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>


    <%---富文本编辑器--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.css"/>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
    <script>
        KindEditor.ready(function (K) {
            var editor1 = K.create('textarea[name="content1"]', {
                cssPath: '${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.css',
                uploadJson: '${pageContext.request.contextPath}/kindeditor/jsp/upload_json.jsp',
                fileManagerJson: '${pageContext.request.contextPath}/kindeditor/jsp/file_manager_json.jsp',
                allowFileManager: true,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            prettyPrint();
        });


    </script>
    <style>
        .modal-content {
            width: 730px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">欢迎来到<strong>持名法洲</strong></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user!=null}">
                    <li>欢迎${sessionScope.user.username}</li>
                    <li><a href="${pageContext.request.contextPath}/login.jsp">退出</a></li>
                </c:if>
                <c:if test="${sessionScope.user==null}">
                    <li><a href="${pageContext.request.contextPath}/register.jsp" data-toggle="modal"
                           data-target="#myModal">注册</a></li>
                    <li><a href="${pageContext.request.contextPath}/login.jsp">登陆</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<hr>

<div class="container" style="height: 500px">
    <%--导航区--%>
    <div class="col-md-2">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="shop1">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#product1" aria-expanded="false" aria-controls="product1">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="product1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="shop4">
                    <div class="panel-body">
                        <li>
                            <a href="javascript:$('#con').load('${pageContext.request.contextPath}/back/lbt.jsp')">所有轮播图</a>
                        </li>
                        <li><a href="">XX轮播图</a></li>
                        <li><a href="">OO轮播图</a></li>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="shop2">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#product2" aria-expanded="false" aria-controls="product2">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="product2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="shop4">
                    <div class="panel-body">
                        <li><a href="javascript:$('#con').load('${pageContext.request.contextPath}/back/album.jsp')">查看专辑</a>
                        </li>
                        <li><a href="">XX专辑</a></li>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="shop3">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#product3" aria-expanded="false" aria-controls="product3">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="product3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="shop4">
                    <div class="panel-body">
                        <li>
                            <a href="javascript:$('#con').load('${pageContext.request.contextPath}/back/article/article-show.jsp')">查看所有文章</a>
                        </li>
                        <%-- <li><a type="button"  data-toggle="modal" data-target="#myModal">添加文章</a></li>--%>
                        <li><a type="button" href="">XX文章</a></li>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="shop4">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#product4" aria-expanded="false" aria-controls="product4">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="product4" class="panel-collapse collapse" role="tabpanel" aria-labelledby="shop4">
                    <div class="panel-body">
                        <li><a href="javascript:$('#con').load('${pageContext.request.contextPath}/back/showuser.jsp')">查看所有用户</a>
                        </li>
                        <li>
                            <a href="javascript:$('#con').load('${pageContext.request.contextPath}/back/registerShow.jsp')">查看所有用户</a>
                        </li>
                        <li>
                            <a href="javascript:$('#con').load('${pageContext.request.contextPath}/back/registerShow2.jsp')">查看所有用户</a>
                        </li>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <%--轮播图区--%>
    <div class="col-md-10" id="con">
        <div class="jumbotron" style="background-color: #9acfea">
            <h2>欢迎光临驰名法洲后台管理系统</h2>
        </div>
        <img src="${pageContext.request.contextPath}/image/shouye.jpg" style="width: 920px" alt="加载中。。。">
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-6">
            <%--<span style="align-content: center">持名法洲后台管理系统&百知研发</span>--%>
        </div>
    </div>
</div>
</body>
</html>
