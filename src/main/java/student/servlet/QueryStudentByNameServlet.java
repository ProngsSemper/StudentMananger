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

@WebServlet("/QueryStudentByNameServlet")
public class QueryStudentByNameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sname = "sname";
        if (request.getSession().getAttribute(sname)==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String name = (String) request.getSession().getAttribute(sname);
        IStudentService studentService = new StudentServiceImpl();
        Student student = studentService.queryStudentByName(name);
        System.out.println(student);
        request.setAttribute("student", student);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }
}
