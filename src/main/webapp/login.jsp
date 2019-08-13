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
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        if (error.equals("addError")) {
            out.print("注册失败！用户已存在！");
        } else if (error.equals("noAddError")) {
            out.print("注册成功！");
        }
    }
%>
<%
    String fail = (String) request.getSession().getAttribute("sname");
    if ("".equals(fail)) {
        out.print("登陆失败！用户名或密码错误！");
    }
%>
<form action="LoginServlet" method="post">
    用户名：<input type="text" name="sname"/><br/>
    密码:<input type="password" name="spwd"/><br/>
    <input type="submit" value="登录"/>
    <input type="submit" value="注册" formaction="add.jsp">
</form>
</body>
</html>