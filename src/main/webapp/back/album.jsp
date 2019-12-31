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
        <table id="album_tt"></table>
        <div id="album_pg" style="height: 50px"></div>
    </div>
</div>
</body>
<script>
    $(function () {
        $("#album_tt").jqGrid(
            {
                styleUI: "Bootstrap",
                url: '${pageContext.request.contextPath}/album/getAlbumByPage',
                datatype: 'json',
                height: 190,
                editurl: '${pageContext.request.contextPath}/album/editAlbum',
                colNames: ['编号', '标题', '封面', '作者', '播音', '分数', '集数', '简介', '时间'],
                colModel: [
                    {name: 'id'},
                    {
                        name: 'title',
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
                        name: 'author',
                        editable: true,
                        edittype: 'text'
                    },
                    {
                        name: 'beam',//播音
                        editable: true,
                        edittype: 'text'
                    },
                    {
                        name: 'score'
                    },
                    {
                        name: 'count'
                    },
                    {
                        name: 'intro',
                        editable: true,
                        edittype: 'text'
                    },
                    {name: 'create_date'}
                ],
                height: 300,
                autowidth: true,
                rowNum: 2,
                rowList: [2, 5, 10, 20],
                pager: '#album_pg',
                viewrecords: true,
                multiselect: false,
                subGrid: true,       //子表
                caption: "展示专辑",
                subGridRowExpanded: function (subgrid_id, row_id) {//1.当前父容器的id   2.当前专辑的id
                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url: '${pageContext.request.contextPath}/album/selectAllChapter?albumId=' + row_id,
                            datatype: "json",
                            colNames: ['编号', '标题', '大小', '时长', '文件名', '创建时间', '在线播放'],
                            editurl: '${pageContext.request.contextPath}/album/editChapter?albumId=' + row_id,
                            colModel: [
                                {name: "id", hidden: true},
                                {
                                    name: "title",  //标题
                                    editable: true,
                                    edittype: 'text'
                                },
                                {name: "size"},   //大小
                                {name: "duration"},//时长
                                {                       //文件名
                                    name: "cover",
                                    index: 'cover',
                                    align: 'center',
                                    editable: true,
                                    edittype: 'file',
                                    formatter: function (cellvalue, options, rowObject) {
                                        return cellvalue;
                                    },
                                    editrules: {edithidden: true}
                                },
                                {name: "create_date"},//创建时间
                                {
                                    name: "aaa",      //播放器
                                    width: 200,
                                    formatter: function (value, options, rows) {
                                        return "<audio controls loop>\n" +
                                            "  <source src='${pageContext.request.contextPath}/music/" + rows.cover + "' type=\"audio/ogg\">\n" +
                                            "</audio>"
                                    }
                                },
                            ],
                            styleUI: "Bootstrap",
                            autowidth: true,
                            rowNum: 2,
                            pager: pager_id,
                            height: '100%'
                        }).jqGrid('navGrid',
                        "#" + pager_id, {add: true, edit: true, del: true, search: true, refresh: true},
                        {//控制章节修改
                            closeAfterEdit: true,// 编辑提交后关闭窗口
                            beforeShowForm: function (frm) {
                                frm.find("#cover").attr("disabled", true); //不允许修改专辑封面
                            }
                        },
                        {
                            closeAfterAdd: true,//控制章节的添加
                            editData: {eid: ''},// 当前请求发送的额外参数, 如果请求参数的key与表单提交数据的key冲突，会覆盖掉表单中提交的
                            afterSubmit: function (data) {
                                console.log("是否进入文件上传222=============" + data);
                                var status = data.responseJSON.status;	//返回的map中的status
                                var mes = data.responseJSON.message;	//返回的map中的message，保存的是插入的banner的id
                                if (status) {
                                    // 文件上传
                                    console.log("---------------开始执行上传-----------------------" + mes);
                                    $.ajaxFileUpload({
                                        url: "${pageContext.request.contextPath}/album/uploadChapter",
                                        type: "post",
                                        fileElementId: "cover",
                                        data: {id: mes},
                                        success: function () {
                                            //刷新页面
                                            $("#" + subgrid_table_id).trigger("reloadGrid");
                                        }
                                    })
                                }
                                return "finish";
                            }

                        },
                        {},//控制删除
                        {
                            closeAfterSearch: true // 搜索提交后关闭窗口
                        }
                    );
                },
            }).navGrid("#album_pg", {edit: true, add: true, del: true, search: false},
            {
                //控制修改的相关操作
                closeAfterEdit: true,
                beforeShowForm: function (frm) {
                    frm.find("#cover").attr("disabled", true); //不允许修改专辑封面
                }
            },
            { //控制添加的相关操作
                closeAfterAdd: true,
                afterSubmit: function (response) {           //上传专辑的封面
                    var status = response.responseJSON.status;
                    var id = response.responseJSON.message;
                    if (status) {
                        $.ajaxFileUpload({
                            type: "post",
                            url: "${pageContext.request.contextPath}/album/uploadAlbum",
                            data: {id: id},
                            fileElementId: "cover",
                            success: function () {
                                $("#album_tt").trigger("reloadGrid"); //添加照片后刷新界面
                            }
                        })
                    }
                    return "finshed";
                }

            });
    })
</script>
</html>
