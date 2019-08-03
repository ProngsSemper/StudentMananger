package student.servlet;

import student.dao.ILoginDao;
import student.dao.impl.ILoginDaoImpl;
import student.entity.Login;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("uname");
        String pwd = request.getParameter("upwd");
        String adm = "administrator";
        Login login = new Login(name, pwd);
        ILoginDao loginDao = new ILoginDaoImpl();
        int result = loginDao.login(login);
        if (name.trim().length() > 0 && pwd.trim().length() > 0) {
            if (adm.equals(name) && adm.equals(pwd)) {
                request.getSession().setAttribute("flag","administrator_login");
                response.sendRedirect("QueryStudentByPageServlet");
            } else if (result > 0) {
                request.getSession().setAttribute("uname",name);
                response.sendRedirect("QueryStudentByNameServlet");
                System.out.println("登录成功");
            } else {
                response.sendRedirect("login.jsp");
            }
        }else {
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
