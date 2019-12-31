<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    th {
        text-align: center;
        background-color: lightblue;
        /*font-family: "华文仿宋";*/
        margin: 0 auto;
        position: relative;
        top: 50%;
        transform: translateY(-10%);

    }

    .ui-jqgrid .ui-jqgrid-title {
        font-size: 10px;
    }

    /*修改grid标题的字体大小*/

    .ui-jqgrid-sortable {
        font-size: 14px;
        font-family: "华文楷体";
    }

    /*修改列名的字体大小*/

    .ui-jqgrid tr.jqgrow td {
        font-size: 12px;
        font-family: "宋体";
    }

    /*修改表格内容字体*/

</style>


<body>

<div class="container" style="height: 500px">
    <%--轮播图区--%>
    <div class="col-md-10">
        <!--引入jqgrid-->
        <table id="tt2"></table>
        <div id="pg2" style="height: 50px"></div>
    </div>
</div>
</body>
<script>
    ///-----------
    $(function () {
        $("#tt2").jqGrid({
            styleUI: "Bootstrap",
            url: '${pageContext.request.contextPath}/articles/showArticles',
            datatype: 'json',
            colNames: ['ID', '标题', '内容', '创建时间', '作者'],//设置表头
            editurl: '${pageContext.request.contextPath}/articles/editArticles',
            colModel: [
                {name: 'id'},
                {
                    name: 'title',
                    editable: true,
                    edittype: 'text'
                },
                {
                    name: 'content'
                },
                {
                    name: 'create_date'
                },
                {
                    name: 'author',
                    editable: true,
                    edittype: 'text'
                }
            ],
            autowidth: true,
            //分页
            pager: "#pg2",
            rowList: [10, 20, 30, 50],
            rowNum: 10,
            page: 1,
            //显示信息总条数
            viewrecords: true,
            sortname: 'id',//排序
            caption: '轮播图列表展示',
            height: 300,
            toolbar: ['true', 'both']

        }).navGrid("#pg2", {add: false, edit: true, del: true, search: true, refresh: true},
            {// 控制编辑的参数
                closeAfterEdit: true// 编辑提交后关闭窗口
            },
            {// 控制添加的参数
                //closeAfterAdd:true // 添加提交后关闭窗口
            },
            {},// 控制删除
            {// 控制搜索
                closeAfterSearch: true // 搜索提交后关闭窗口
            })
    })

</script>
</html>
