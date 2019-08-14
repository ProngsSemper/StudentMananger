package student.servlet;

import student.dao.ILoginDao;
import student.dao.impl.LoginDaoImpl;
import student.entity.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Prongs
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("sname");
        String pwd = request.getParameter("spwd");
        String adm = "administrator";
        Login login = new Login(name, pwd);
        ILoginDao loginDao = new LoginDaoImpl();
        int result = loginDao.login(login);
        if (name.trim().length() > 0 && pwd.trim().length() > 0) {
            /*
            判断管理员登录还是学生登录，并用flag储存登录身份放到session里面
            */
            if (adm.equals(name) && adm.equals(pwd)) {
                request.getSession().setAttribute("flag", "administrator_login");
                out.write("adm_login");
            } else if (result > 0) {
                request.getSession().setAttribute("flag", "student_login");
                /*
                 将登录学生姓名放入session中，用作防止空值登录
                 */
                request.getSession().setAttribute("sname", name);
                out.write("stu_login");
                System.out.println("登录成功");
            } else {
                out.write("false");
                System.out.println("登陆失败");
            }
        } else {
            out.write("false");
            System.out.println("登陆失败");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
