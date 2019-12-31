<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">

    <%--引入jquery的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>

</head>
<body>

<div class="container" style="height: 500px">
    <!--引入jqgrid-->
    <div id="main" style="width: 600px;height:400px;"></div>
</div>
</body>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '近三周用户注册信息展示'
        },
        tooltip: {},
        legend: {
            data: ['男生', '女生']
        },
        xAxis: {
            data: ["第一周", "第二周", "第三周"]
        },
        yAxis: {},
        series: [{
            name: '男生',
            type: 'bar'
        }, {
            name: '女生',
            type: 'bar'
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        type: 'get',
        url: '${pageContext.request.contextPath}/user/getUserThreed',
        dataType: "json",
        success: function (data) {
            myChart.setOption({
                series: [{
                    //服务器返回list集合或者数组 || 该处手动调整
                    data: data[0]
                }, {
                    data: data[1]
                }]
            })
        }
    })

    var goEasy = new GoEasy({
        appkey: "BS-e91d80e641da46cc899d01116ac4e90f"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            console.log("执行到这里" + message.content);
            var list = JSON.parse(message.content);
            myChart.setOption({
                series: [{
                    //服务器返回list集合或者数组 || 该处手动调整
                    data: list[0]
                }, {
                    data: list[1]
                }]
            })
        }
    });
</script>
</html>
