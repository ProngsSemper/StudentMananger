<%--
  Created by IntelliJ IDEA.
  User: P7XXTM1-G
  Date: 2019/7/31
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎来到学籍管理系统，请先登录</title>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        function login() {
            var data = $("#login").serialize();
            $.ajax({
                url: "LoginServlet",
                type: "post",
                data: data,
                success: function (result, testStatus) {
                    if (result == "adm_login") {
                        alert("登录成功！");
                        window.location.href = 'QueryStudentByPageServlet';
                    } else if (result == "stu_login") {
                        alert("登录成功！");
                        window.location.href = 'QueryStudentByNameServlet';
                    } else {
                        alert("登录失败！请检查用户名或密码是否有误！");
                        window.location.href = 'login.jsp';
                    }
                },
                error: function (xhr, errorMessage, e) {
                    alert("系统异常");
                }
            });
        }

        function register() {
            window.location.href = 'add.jsp';
        }
    </script>
</head>
<body>
<form id="login">
    用户名：<input type="text" name="sname"/><br/>
    密码:<input type="password" name="spwd"/><br/>
    <input type="button" value="登录" onclick="login()"/>
    <input type="button" value="注册" onclick="register()"/>
</form>
</body>
</html>