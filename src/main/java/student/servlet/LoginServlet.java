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
        String name = request.getParameter("sname");
        String pwd = request.getParameter("spwd");
        String adm = "administrator";
        Login login = new Login(name, pwd);
        ILoginDao loginDao = new ILoginDaoImpl();
        int result = loginDao.login(login);
        if (name.trim().length() > 0 && pwd.trim().length() > 0) {
            if (adm.equals(name) && adm.equals(pwd)) {
                request.getSession().setAttribute("flag", "administrator_login");
                response.sendRedirect("QueryStudentByPageServlet");
            } else if (result > 0) {
                request.getSession().setAttribute("sname", name);
                response.sendRedirect("QueryStudentByNameServlet");
                System.out.println("登录成功");
            } else {
                request.getSession().setAttribute("sname", "");
                response.sendRedirect("login.jsp");
                System.out.println("登陆失败");
            }
        } else {
            request.getSession().setAttribute("sname", "");
            response.sendRedirect("login.jsp");
            System.out.println("登陆失败");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
