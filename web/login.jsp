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
</head>
<body>
<form action="LoginServlet">
    用户名：<input type="text" name="uname"/><br/>
    密码:<input type="password" name="upwd"/><br/>
    <input type="submit" value="登录"/><br/>
</form>
</body>
</html>