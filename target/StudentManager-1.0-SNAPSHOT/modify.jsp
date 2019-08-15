<%@ page import="student.entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: Prongs
  Date: 2019/8/3
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        //修改学生信息
        function update() {
            var data = $("#update").serialize();
            $.ajax({
                url: "UpdateStudentServlet",
                type: "get",
                data: data,
                success: function (result, testStatus) {
                    if (result == "adm_update") {
                        alert("修改成功！");
                        window.location.href = 'QueryStudentByPageServlet';
                    } else if (result == "stu_update") {
                        alert("修改成功！");
                        window.location.href = 'QueryStudentByNameServlet';
                    } else {
                        alert("修改失败！请检查数据类型是否写错！");
                    }
                },
                error: function (xhr, errorMessage, e) {
                    alert("系统异常");
                }
            });
        }

        //修改学生头像
        function upload() {
            $.ajax({
                url: "UploadServlet",
                type: "post",
                cache: false,
                data: new FormData($("#upload")[0]),
                processData: false,
                contentType: false,
                success: function (result, testStatus) {
                    if (result == "adm_success") {
                        alert("头像上传成功！");
                        window.location.href = 'QueryStudentByPageServlet';
                    } else if (result == "uploadError") {
                        alert("上传失败！图片类型有误！");
                    } else if (result == "stu_success") {
                        alert("头像上传成功！");
                        window.location.href = 'QueryStudentByNameServlet';
                    }
                },
                error: function (xhr, errorMessage, e) {
                    alert("系统异常");
                }
            });
        }
    </script>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("students");
    request.getSession().setAttribute("imgSno", student.getSno());
%>
<form enctype="multipart/form-data" id="upload">
    <%
        if (student.getSimg() != null) {
    %>
    <img alt="无法显示图片" src="upload\\<%=student.getSimg()%>" height="100px" width="100px"><br/>
    <%
        }
    %>
    上传/修改头像：<input type="file" name="simg"/><br/>
    <input type="button" value="上传" onclick="upload()">
</form>
<form id="update">
    学号：<input type="text" name="sno" value="<%=student.getSno()%>" readonly="readonly"/><br/>
    姓名：<input type="text" name="sname" value="<%=student.getSname()%>"/><br/>
    年龄：<input type="text " name="sage" value="<%=student.getSage()%>"/><br/>
    性别：<select name="sgender">
    <%
        if (student.getSgender().equals("女")) {
    %>
    <option selected><%=student.getSgender()%>
    </option>
    <option value="男">男</option>
    <%
    } else {
    %>
    <option selected><%=student.getSgender()%>
    </option>
    <option value="女">女</option>
    <%
        }
    %>
</select><br/>
    电话号码：<input type="text" name="snum" value="<%=student.getSnum()%>"/><br/>
    地址：<input type="text" name="saddress" value="<%=student.getSaddress()%>"/><br/>
    密码：<input type="password" name="spassword" value="<%=student.getSpassword()%>"/><br/>
    <input type="button" value="修改" onclick="update()">
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
