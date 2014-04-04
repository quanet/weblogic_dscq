<%@ page import="cn.com.restarter.web.TaskController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8"/>
    <title>Weblogic重启界面</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
</head>
<body>
<input name="restart" id="restart" type="button" value="重启" onclick="restart();">
<input name="downloadlog" id="downloadlog" type="button" value="下载本应用日志文件" onclick="downloadlog();">
<input name="downloadlogALL" id="downloadlogALL" type="button" value="下载所配置应用日志文件" onclick="downloadlogALL();">
<table>
    <c:forEach var="weblogic" items="${weblogiclist}">
        <tr>
            <td>
                    ${weblogic}
            </td>
            <td>
                <input type="button" value="重启" onclick="restartOne('${weblogic}')">
            </td>
            <td>
                <input type="button" value="下载日志" onclick="downloadlogOne('${weblogic}')">
            </td>
        </tr>
    </c:forEach>
</table>

</body>
<script>
    function restart() {
        var count =   <%=TaskController.getCount()%>;
        if (count == 0) {
            alert("还没有配置任何应用，请配置！");
        } else {
            alert("重启成功，请勿多次点击！！");
            document.getElementById("restart").disabled = true;

            $.ajax({
                type: "POST",
                url: "/basic/web/task/restart.action",
                async: true,
                cache: false,
                dataType: 'json',
                success: function (data) {
                },
                error: function () {
                }
            });
        }
    }

    function downloadlog() {
        window.location.href = "/basic/web/task/downloadlog.action";
    }

    function downloadlogALL() {
        var count =   <%=TaskController.getCount()%>;
        if (count == 1) {
            alert("还没有配置任何应用，请配置！");
        } else {
            for (var i = 1; i < count; i++) {
                window.open("/basic/web/task/downloadlogALL.action?count=" + i);
            }
        }
    }
    //重启一个应用
    function restartOne(url) {
        alert("重启成功，请勿多次点击！！");
        $.ajax({
            type: "POST",
            url: "/basic/web/task/restartOne.action",
            data: {'url':url },
            async: true,
            cache: false,
            dataType: 'json',
            success: function (data) {
            },
            error: function () {
            }
        });
    }
    //下载一个应用的日志
    function downloadlogOne(url) {
        window.location.href = "/basic/web/task/downloadlogOne.action?url="+url;

    }
</script>
</html>
