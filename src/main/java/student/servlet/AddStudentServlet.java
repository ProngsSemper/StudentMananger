package student.servlet;

import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

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
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        int no = Integer.parseInt(request.getParameter("sno"));
        String name = request.getParameter("sname");
        int age = Integer.parseInt(request.getParameter("sage"));
        String address = request.getParameter("saddress");
        String password = request.getParameter("spassword");
        int num = Integer.parseInt(request.getParameter("snum"));
        String gender = request.getParameter("sgender");
        Student student = new Student(no, name, age, address, password, num, gender);
        String adm = "administrator_login";
        String flag = "flag";

        IStudentService studentService = new StudentServiceImpl();
        boolean result = studentService.addStudent(student);
        /*
         判断是学生还是管理员进行的增加学生操作
         */
        if (!result) {
            if (adm.equals(request.getSession().getAttribute(flag))) {
                out.write("adm_false");
            } else {
                out.write("false");
            }
        } else {
            if (adm.equals(request.getSession().getAttribute(flag))) {
                out.write("adm_true");
            } else {
                out.write("true");
            }
        }
    }
}