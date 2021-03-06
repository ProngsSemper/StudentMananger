<%@ page import="student.entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: Prongs
  Date: 2019/8/3
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生</title>
</head>
<body>
<table border="1px">
    <tr>
        <th>头像</th>
        <th>学号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
        <th>电话号码</th>
        <th>地址</th>
        <th>密码</th>
        <th>操作</th>
    </tr>
    <%
        /*
        防止空值登录
         */
        String sname = "sname";
        if (request.getSession().getAttribute(sname) == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    %>
    <%
        Student student = (Student) request.getSession().getAttribute("student");
    %>
    <tr>
        <td>
            <%
                if (!(student.getSimg() == null)) {
            %>
            <img alt="无法显示图片" src="upload\\<%=student.getSimg()%>" height="100px" width="100px"><br/>
            <%
                } else {
                    out.print("暂无头像！");
                }
            %>
        </td>
        <td><%=student.getSno()%>
        </td>
        <td><%=student.getSname()%>
        </td>
        <td><%=student.getSage()%>
        </td>
        <td><%=student.getSgender()%>
        </td>
        <td><%=student.getSnum()%>
        </td>
        <td><%=student.getSaddress()%>
        </td>
        <td><%=student.getSpassword()%>
        </td>
        <td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>">修改</a></td>
    </tr>
</table>
<a href="LogoutServlet">注销</a>
</body>
</html>
