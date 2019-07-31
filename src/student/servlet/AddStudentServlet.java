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

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        int no = Integer.parseInt(request.getParameter("sno"));
        String name = request.getParameter("sname");
        int age = Integer.parseInt(request.getParameter("sage"));
        String address = request.getParameter("saddress");
        Student student = new Student(no, name, age, address);

        IStudentService studentService = new StudentServiceImpl();
        boolean result = studentService.addStudent(student);
        PrintWriter out = response.getWriter();
//        if (result) {
//
//            response.sendRedirect("QueryAllStudentsServlet");
//        } else {
//            out.println("增加失败！");
//        }
        if (!result) {
            request.setAttribute("error", "adderror");
        }else{
            request.setAttribute("error","noadderror");
        }
        request.getRequestDispatcher("QueryStudentByPageServlet").forward(request, response);
    }
}
