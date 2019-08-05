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
import java.util.List;

@WebServlet("/QueryConditionalServlet")
public class QueryConditionalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        IStudentService studentService = new StudentServiceImpl();
        String sname = request.getParameter("name");
        String saddress = request.getParameter("address");
        List<Student> students = studentService.queryConditional(sname,saddress);
        request.getSession().setAttribute("students",students);
        request.getRequestDispatcher("conditional.jsp").forward(request, response);
    }
}
