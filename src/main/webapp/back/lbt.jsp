<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container" style="height: 500px">
    <%--轮播图区--%>
    <div class="col-md-10">
        <!--引入jqgrid-->
        <table id="tt"></table>
        <div id="pg" style="height: 50px"></div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#tt").jqGrid({
            styleUI: "Bootstrap",
            url: '${pageContext.request.contextPath}/lbt/getLbtByPage',
            datatype: 'json',
            colNames: ['ID', 'Name', 'Cover', 'Describe', 'Status', 'Create_time', 'Operate'],//设置表头
            editurl: '${pageContext.request.contextPath}/lbt/editLbt',
            colModel: [
                {name: 'id'},
                {
                    name: 'name',
                    editable: true,
                    edittype: 'text'
                },
                {
                    name: 'cover',
                    index: 'cover',
                    align: 'center',
                    editable: true,
                    edittype: 'file',
                    formatter: function (cellvalue, options, rowObject) {
                        return '<img src=${pageContext.request.contextPath}/image/' + cellvalue + ' style="width: 50px;height: 50px;"/>';
                    },
                    editrules: {edithidden: true}
                },
                {
                    name: 'describes',
                    editable: true,
                    edittype: 'text'
                },
                {
                    name: 'status',
                    editable: true,
                    formatter: function (rowObject) {
                        console.log(rowObject);
                        var level;
                        if (rowObject == 1) {
                            level = "<option>激活</option>";
                        } else if (rowObject == 2) {
                            level = "<option>禁用</option>";
                        }
                        return level;
                    },
                    //单元格修改状态
                    edittype: 'select',
                    editoptions: {value: "1:激活; 2:禁用"}

                },
                {
                    name: 'create_time'
                },
                {
                    name: 'operate', index: 'operate', align: "center",
                    formatter: function (value, grid, rows, state) {
                        return '<div style="text-align: center"><button id="value" class="btn-info" onclick="fun()" >删除</button>' +
                            '&nbsp;&nbsp;&nbsp;<button id="" class="btn-danger" onclick="fun()" >修改</button></div>';
                    }
                }
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

        }).navGrid("#pg", {add: true, edit: true, del: true, search: true, refresh: true},
            {// 控制编辑的参数
                closeAfterEdit: true,// 编辑提交后关闭窗口
                beforeShowForm: function (frm) {
                    frm.find("#cover").attr("disabled", true);
                }
            },
            {// 控制添加的参数
                closeAfterAdd: close, // 添加提交后关闭窗口
                editData: {eid: ''},// 当前请求发送的额外参数, 如果请求参数的key与表单提交数据的key冲突，会覆盖掉表单中提交的
                afterSubmit: function (data) {
                    console.log("是否进入文件上传222=============" + data);
                    var status = data.responseJSON.status;	//返回的map中的status
                    var mes = data.responseJSON.message;	//返回的map中的message，保存的是插入的banner的id
                    if (status) {
                        // 文件上传
                        console.log("---------------开始执行上传-----------------------" + mes);
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/lbt/upload",
                            type: "post",
                            fileElementId: "cover",
                            data: {id: mes},
                            success: function () {
                                //刷新页面
                                $("#tt").trigger("reloadGrid");
                            }
                        })
                    }
                    return "finish";
                }

            },
            {},// 控制删除
            {// 控制搜索
                closeAfterSearch: true // 搜索提交后关闭窗口
            });
    })

</script>
</html>
