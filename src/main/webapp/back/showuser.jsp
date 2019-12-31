<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<%--引入jqgrid的js文件--%>

<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>

<script src="${pageContext.request.contextPath}/statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>

<script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>


<body>

<div class="container" style="height: 500px">
    <%--导航标签--%>
    <div>

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">展示用户</a>
            </li>
            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="outPut()"
                                       data-toggle="tab">导出用户</a></li>
        </ul>
    </div>
    <!--引入jqgrid-->
    <table id="tt"></table>
    <div id="pg" style="height: 50px"></div>
</div>
</body>
<script>
    $(function () {
        $("#tt").jqGrid({
            styleUI: "Bootstrap",
            url: '${pageContext.request.contextPath}/user/getAllUser',
            datatype: 'json',
            cellEdit: true,
            cellurl: '${pageContext.request.contextPath}/user/editUser',
            colNames: ['ID', 'Name', 'Password', 'Salt', 'Dharma', 'Province', 'City', 'Sign', "Sex", 'Photo', 'Status', 'Phone', 'Create_date', 'Fname'],//设置表头
            colModel: [
                {name: 'id'},
                {name: 'username'},
                {name: 'password'},
                {name: 'salt'},
                {name: 'dharma'},
                {name: 'province'},
                {name: 'city'},
                {name: 'sign'},
                {name: 'sex'},
                {
                    name: 'photo',
                    index: 'photo',
                    align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        return '<img src=${pageContext.request.contextPath}/image/' + cellvalue + ' style="width: 50px;height: 50px;"/>';
                    },
                    editrules: {edithidden: true}
                },
                {
                    name: 'status',
                    editable: true,
                    //单元格修改状态
                    edittype: 'select',
                    editoptions: {value: "激活:激活; 禁用:禁用"}
                },
                {name: 'phone'},
                {name: 'create_date'},
                {name: 'fname'}
            ],
            autowidth: true,
            //分页
            pager: "#pg",
            rowList: [10, 20, 30, 50],
            rowNum: 10,
            page: 1,
            //显示信息总条数
            viewrecords: true,
            sortname: 'id',//排序
            caption: '轮播图列表展示',
            height: 300,
            toolbar: ['true', 'both']

        }).navGrid("#pg", {add: false, edit: false, del: false, search: true, refresh: true},
            {// 控制编辑的参数
                // closeAfterEdit:true// 编辑提交后关闭窗口

            },
            {// 控制添加的参数
            },
            {},// 控制删除
            {// 控制搜索
                closeAfterSearch: true // 搜索提交后关闭窗口
            });
    })

    function outPut() {
        //文件的导出，必须发送同步请求
        window.location.href = "${pageContext.request.contextPath}/user/outPutUser";
    }

</script>
</html>
