<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $('[data-toggle="popover"]').popover()
        })
    </script>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


<div class="container" style="height: 500px">
    <%--导航区--%>
    <div class="col-md-2">
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

            <%--<c:forEach items="${requestScope.categorys}" var="cate">
                <div class="panel panel-default">
                    <!--一级目录-->
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#${cate.cid}" aria-expanded="false" aria-controls="collapseTwo">
                                ${cate.cname}
                            </a>
                        </h4>
                    </div>
                    <!--二级目录-->
                    <div id="${cate.cid}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <c:forEach items="${cate.categoryList}" var="cate2">
                                <li><a href="${pageContext.request.contextPath}/shop/cate2Products?cid=${cate2.cid}">${cate2.cname}</a></li>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </c:forEach>--%>

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
                        <li><a href="">所有轮播图</a></li>
                        <li><a href="">禁用轮播图</a></li>
                        <li><a href="">启用轮播图</a></li>
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
                        <li><a href="">查看专辑</a></li>
                        <li><a href="">修改专辑</a></li>
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
                        <li><a href="">查看所有文章</a></li>
                        <li><a href="">修改图</a></li>
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
                        <li><a href="">查看所有用户</a></li>
                        <li><a href="">xxx用户</a></li>
                        <li><a href="">ooo用户</a></li>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <%--轮播图区--%>
    <div class="col-md-10">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox" style="background-color: #999999;width: 100%;height: 450px">
                <div class="item active">
                    <img src="${pageContext.request.contextPath}/image/b15.jpg" alt="..."
                         style="width: 100%;height: 100%">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/image/b12.jpg" alt="..."
                         style="width: 100%;height: 100%">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/image/b11.jpg" alt="..."
                         style="width: 100%;height: 100%">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                ...
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

</div>
<footer>
    <div class="container">
        <div class="col-md-4 col-md-offset-6">
            <span style="align-content: center">持名法洲后台管理系统&百知研发</span>
        </div>
    </div>
</footer>
</body>
</html>
