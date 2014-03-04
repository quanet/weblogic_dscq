<%@ page import="cn.com.restarter.web.TaskController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weblogic重启界面</title>
</head>
<body>
<%
    if (request.getParameter("type") != null) {
%>
重启weblogic服务器成功！<br>
<%
    }
%>
<input name="restart" id="restart" type="button" value="重启" onclick="restart();">
<input name="restart" id="restart" type="button" value="下载本应用日志文件" onclick="downloadlog();">
<input name="restart" id="restart" type="button" value="下载所配置应用日志文件" onclick="downloadlog1();">
</body>
<script>
    function restart() {
        var count =   <%=TaskController.getCount()%>;
        if (count == 0) {
            alert("还没有配置任何应用，请配置！");
        } else {
            document.getElementById("restart").disabled = true;
            window.location.href = "/basic/web/task/restart.action";
        }
    }

    function downloadlog() {
        window.location.href = "/basic/web/task/downloadlog.action";
    }

    function downloadlog1() {
        var count =   <%=TaskController.getCount()%>;
        if (count == 1) {
            alert("还没有配置任何应用，请配置！");
        } else {
            for (var i = 1; i < count; i++) {
                window.open("/basic/web/task/downloadlog1.action?count=" + i);
            }
        }

    }
</script>
</html>
