<%--
  Created by IntelliJ IDEA.
  User: Prongs
  Date: 2019/7/21
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        //增加/注册学生
        function register() {
            var data = $("#add").serialize();
            $.ajax({
                url: "AddStudentServlet",
                type: "get",
                data: data,
                success: function (result, testStatus) {
                    if (result == "adm_true") {
                        alert("新增成功！");
                        window.location.href = 'QueryStudentByPageServlet';
                    } else if (result == "adm_false") {
                        alert("新增用户失败！用户可能已存在！");
                        window.location.href = 'QueryStudentByPageServlet';
                    } else if (result == "true") {
                        alert("注册成功！");
                        window.location.href = 'login.jsp';
                    } else {
                        alert("注册失败！用户可能已存在！");
                        window.location.href = 'login.jsp';
                    }
                },
                error: function (xhr, errorMessage, e) {
                    alert("系统异常！请检查数据类型是否填错！");
                }
            });
        }

        function goBack() {
            window.location.href = 'administrator.jsp';
        }
    </script>
</head>
<body>
<form id="add">
    学号：<input type="text" name="sno"/><br/>
    姓名：<input type="text" name="sname"/><br/>
    年龄：<input type="text" name="sage"/><br/>
    性别：<select name="sgender">
    <option value="男">男</option>
    <option value="女">女</option>
</select><br/>
    电话号码：<input type="text" name="snum"/><br/>
    地址：<input type="text" name="saddress"/><br/>
    密码：<input type="password" name="spassword"/><br/>
    <input type="button" value="注册" onclick="register()"/>
    <input type="button" value="返回" onclick="goBack()"/><br/>
</form>
</body>
</html>
