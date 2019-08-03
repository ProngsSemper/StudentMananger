<%@ page import="student.entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: P7XXTM1-G
  Date: 2019/8/3
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("students");
%>
<form action="UpdateStudentServlet">
    学号：<input type="text" name="sno" value="<%=student.getSno()%>" readonly="readonly"/><br/>
    姓名：<input type="text" name="sname" value="<%=student.getSname()%>"/><br/>
    年龄：<input type="text " name="sage" value="<%=student.getSage()%>"/><br/>
    地址：<input type="text" name="saddress" value="<%=student.getSaddress()%>"/><br/>
    密码：<input type="password" name="spassword" value="<%=student.getSpassword()%>"/><br/>
    <input type="submit" value="修改">
    <%
        String adm = "administrator_login";
        String flag = "flag";
        if (adm.equals(request.getSession().getAttribute(flag))) {
    %>
    <a href="QueryStudentByPageServlet">返回</a>
    <%
    } else {
    %>
    <a href="QueryStudentByNameServlet">返回</a>
    <%
        }
    %>
</form>
</body>
</html>
