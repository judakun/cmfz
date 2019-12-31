<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <title>Document</title>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
</head>
<body>
<span>
    <p>hhhhhhhhhhhhhhhhhhhh</p>
</span>
<script>
    var goEasy = new GoEasy({
        appkey: "BS-e91d80e641da46cc899d01116ac4e90f"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            console.log("执行到这里");
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });
</script>
</body>
</html>