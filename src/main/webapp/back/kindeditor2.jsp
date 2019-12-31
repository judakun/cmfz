<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <title>Document</title>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function (K) {
            KindEditor.create('#editor_id', {
                allowFileManager: true,
                uploadJson: "${pageContext.request.contextPath}/articles/upload",
                filePostName: "img",
                fileManagerJson: "${pageContext.request.contextPath}/articles/getAll"
            });
        });
    </script>
</head>
<body>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
    &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>
<input type="text" placeholder="请输入"/>
</body>
</html>