<%@ page import="cn.com.restarter.web.TaskController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf8" />
    <title>Weblogic重启界面</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js"></script>
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
            alert("重启成功，请勿多次点击！！");
            document.getElementById("restart").disabled = true;

            $.ajax({
                type: "POST",
                url: "/basic/web/task/restart.action",
                async: true,
                cache: false,
                dataType:'json',
                success: function(data){
                 alert("成功");
                },
                error:function(){
                    alert("失败");
                }
            });
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
