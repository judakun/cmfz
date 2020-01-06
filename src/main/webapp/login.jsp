<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed"
          href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="${pageContext.request.contextPath}/assets/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
    <script>
        $(function () {
            $("#captchaImage").click(function () {
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/user/getCode?time=" + new Date().getTime());
            });
        });
    </script>
</head>

<body>
<a href="javascript:$('#xxx').load('${pageContext.request.contextPath}/index.jsp')">点我加载。。。</a>
<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>持名法洲</strong> Login </h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>登陆</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px" id="xxx">
                        <div class="form-top-left">
                            <h3>欢迎使用</h3>
                            <span id="check1" style="color: red"></span>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="" class="login-form" id="loginForm">
                            <span id="check" style="color: red"></span>
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" required>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       minlength="2" class="form-password form-control" id="form-password" required>
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/user/getCode">
                                <input style="width: 286px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="test" name="enCode" id="form-code" required>
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                                   id="loginButtonId" value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#loginButtonId').click(function () {
            console.log("成功触发提交事件---------------------");
            //获取表单
            var objdom = $('#loginForm')[0];
            var formdata = new FormData(objdom);
            //执行数据发送
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/adminLogin',
                data: formdata,
                contentType: false,// 不去处理请求头的类型，默认使用表单上声明
                processData: false,// 以传统方式处理表单的数据
                success: function (data) {
                    if (data == 'success') {
                        window.location.href = '${pageContext.request.contextPath}/back/back.jsp';
                    } else {
                        $('#check').innerText = data;
                    }

                },
                error: function () {
                    console.log("逻辑错误！！！")
                }
            })
        })
    })


</script>


</body>

</html>
