<%@ page import="student.entity.Page" %>
<%@ page import="student.entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: P7XXTM1-G
  Date: 2019/7/21
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员</title>
</head>
<body>
<%
    String adm = "administrator_login";
    String flag = "flag";
    if (!(adm.equals(request.getSession().getAttribute(flag)))) {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
%>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        if (error.equals("adderror")) {
            out.print("增加失败！");
        } else if (error.equals("noadderror")) {
            out.print("增加成功");
        }
    }
%>
<form action="QueryConditionalServlet">
    按条件查询
    姓名：<input type="text" name="name">
    地址：<input type="text" name="address">
    <input type="submit" value="查询"><br/>
</form>
<table border="1px">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>地址</th>
        <th>密码</th>
        <th>操作</th>
        <th>操作</th>
    </tr>
    <%
        Page pages = (Page) request.getSession().getAttribute("page");
        for (Student student : pages.getStudents()) {
    %>
    <tr>
        <td><%=student.getSno()%>
        </td>
        <td><%=student.getSname()%>
        </td>
        <td><%=student.getSage()%>
        </td>
        <td><%=student.getSaddress()%>
        </td>
        <td><%=student.getSpassword()%>
        </td>
        <td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>">修改</a></td>
        <td><a href="DeleteStudentServlet?sno=<%=student.getSno()%>">删除</a></td>
    </tr>
    <%
        }
    %>
</table>
<a href="add.jsp">新增</a><br/>
<%
    if (pages.getTotalPage() == 1) {
%>
<a href="LogoutServlet">注销</a>
<% } else if (pages.getTotalPage() - 1 == pages.getCurrentPage()) {
%>
<a href="/QueryStudentByPageServlet?currentPage=0">首页</a>
<a href="/QueryStudentByPageServlet?currentPage=<%=pages.getCurrentPage()-1%>">上一页</a>
<a href="LogoutServlet">注销</a>
<% } else if (pages.getCurrentPage() == 0) {
%>
<a href="/QueryStudentByPageServlet?currentPage=<%=pages.getCurrentPage()+1%>">下一页</a>
<a href="/QueryStudentByPageServlet?currentPage=<%=pages.getTotalPage()-1%>">尾页</a>
<a href="LogoutServlet">注销</a>
<%
} else {
%>
<a href="/QueryStudentByPageServlet?currentPage=0">首页</a>
<a href="/QueryStudentByPageServlet?currentPage=<%=pages.getCurrentPage()-1%>">上一页</a>
<a href="/QueryStudentByPageServlet?currentPage=<%=pages.getCurrentPage()+1%>">下一页</a>
<a href="/QueryStudentByPageServlet?currentPage=<%=pages.getTotalPage()-1%>">尾页</a>
<a href="LogoutServlet">注销</a>
<%
    }
%>
</body>
</html>
