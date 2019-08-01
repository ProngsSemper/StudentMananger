package student.servlet;

import student.dao.LoginDao;
import student.entity.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("uname");
        String pwd = request.getParameter("upwd");
        Login login = new Login(name, pwd);
        int result = LoginDao.login(login);
        if ("administrator".equals(name)&&"administrator".equals(pwd)) {
            response.sendRedirect("QueryStudentByPageServlet");
        }else if (result > 0) {
            System.out.println("登录成功");
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
}
