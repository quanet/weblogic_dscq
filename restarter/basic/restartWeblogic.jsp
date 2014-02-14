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
<input name="restart" id="restart" type="button" value="下载日志文件" onclick="">
</body>
<script>
    function restart() {
        document.getElementById("restart").disabled = true;
        window.location.href = "/basic/web/task/restart.action";
    }
</script>
</html>
