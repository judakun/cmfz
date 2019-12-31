<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>
    <meta charset="utf-8">
    <%--引入jquery的js文件--%>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
</head>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<body>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
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
            var v = new Array();
            $.each(data, function (i, item) {
                //i=key  item=value
                v.push(item);
                console.log(i + "///" + item);
            })
            myChart.setOption({
                series: [{
                    //服务器返回list集合或者数组 || 该处手动调整
                    data: v[1]
                }, {
                    data: v[0]
                }]
            })
        }
    })
</script>


</body>

</html>
