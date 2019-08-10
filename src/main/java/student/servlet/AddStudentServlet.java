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
        int no = Integer.parseInt(request.getParameter("sno"));
        String name = request.getParameter("sname");
        int age = Integer.parseInt(request.getParameter("sage"));
        String address = request.getParameter("saddress");
        String password = request.getParameter("spassword");
        Student student = new Student(no, name, age, address, password);
        String adm = "administrator_login";
        String flag = "flag";

        IStudentService studentService = new StudentServiceImpl();
        boolean result = studentService.addStudent(student);
        if (!result) {
            request.setAttribute("error", "addError");
        } else {
            request.setAttribute("error", "noAddError");
        }

        if (adm.equals(request.getSession().getAttribute(flag))) {
            request.getRequestDispatcher("QueryStudentByPageServlet").forward(request, response);
        }else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }
}