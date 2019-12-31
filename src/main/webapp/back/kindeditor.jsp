<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
    <script>
        $(function () {
            $("#article-table").jqGrid({
                url: '${pageContext.request.contextPath}/articles/showArticles',
                datatype: "json",
                colNames: ['编号', '标题', '作者', '内容', '时间', '操作'],
                colModel: [
                    {name: 'id', hidden: true},
                    {name: 'title', editable: true},
                    {name: 'author', editable: true},
                    {name: 'content', editable: true},
                    {name: 'create_date'},
                    //自定义标签，
                    {
                        name: 'aaa', formatter: function (value, options, rows) {
                            return "<a class='btn btn-success' onclick=\"openModal('edit','" + rows.id + "')\" >修改文章</a>"
                            //return "<a class='btn btn-success' onclick=\"openModal('edit','"+rows.id+"')\" >修改文章</a>"
                        }
                    }
                ],
                styleUI: "Bootstrap",
                height: 300,
                autowidth: true,
                rowNum: 2,
                rowList: [2, 5, 10],
                pager: '#article-pager',
                viewrecords: true,
                caption: "展示文章数据",
                editurl: "${pageContext.request.contextPath}/article/edit"
            }).navGrid("#article-pager", {edit: false, add: false, del: true, search: false})
        })

        function openModal(oper, id) {
            //点击修改触发函数
            KindEditor.html("#editor_id", "");//在修改时，将kindeditor文本框置空。
            var article = $("#article-table").jqGrid("getRowData", id);//获取jqGrid中指定id行的数据
            $("#article-id").val(article.id);
            $("#article-title").val(article.title);
            $("#article-author").val(article.author);
            //对kindedor编辑器进行赋值
            KindEditor.html("#editor_id", article.content);
            $("#article-oper").val(oper);
            //显示模态框
            $("#article-modal").modal("show");
        }

        //初始化kindeditor
        KindEditor.create("#editor_id", {
            //设置kindeditor属性
            allowFileManager: true,
            uploadJson: "${pageContext.request.contextPath}/articles/upload",
            filePostName: "img",
            fileManagerJson: "${pageContext.request.contextPath}/articles/getAll",
            //设置文本框的拖动，只允许上下拖动，不允许左右拖动
            resizeType: 1,
            //将文本域中的值同步到form当中
            afterBlur: function () {
                this.sync();
            }

        });

        function dealSave() {
            console.log("点击保存触发该函数：")
            $.ajax({
                url: "${pageContext.request.contextPath}/articles/edit",
                type: "post",
                data: {
                    id: $("#article-id").val(),
                    oper: $("#article-oper").val(),
                    title: $("#article-title").val(),
                    author: $("#article-author").val(),
                    content: $(document.getElementsByTagName('iframe')[0].contentWindow.document.body).html()
                },
                dataType: "json",
                success: function () {
                    $("#article-modal").modal("hide");
                    $("#article-table").trigger("reloadGrid");
                }
            })
        }
    </script>
</head>
<body>
<%--标签页--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="openModal('add')"
                                   data-toggle="tab">添加文章</a></li>
    </ul>
</div>
<table id="article-table"></table>
<div id="article-pager" style="height: 80px"></div>
<%--模态框--%>
<div class="modal fade" id="article-modal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 702px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">请输入文章信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="article-form">
                    <!---->
                    <input id="article-id" type="hidden" name="id">
                    <input id="article-oper" type="hidden" name="oper">
                    <div class="form-group">
                        <label for="article-title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" name="title" class="form-control" id="article-title" placeholder="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article-author" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input type="text" name="author" class="form-control" id="article-author"
                                   placeholder="author">
                        </div>
                    </div>
                    <div class="form-group">
                        <!--kindeditor文本框-->
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                         </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="dealSave()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>