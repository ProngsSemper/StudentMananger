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

@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String adm = "administrator_login";
        String flag = "flag";
        request.getParameter("sno");
        try {
            int no = Integer.parseInt(request.getParameter("sno"));
            String name = request.getParameter("sname");
            int age = Integer.parseInt(request.getParameter("sage"));
            String address = request.getParameter("saddress");
            String password = request.getParameter("spassword");
            int num = Integer.parseInt(request.getParameter("snum"));
            String gender = request.getParameter("sgender");
            Student student = new Student(name, age, address, password, num, gender);
            IStudentService studentService = new StudentServiceImpl();
            boolean result = studentService.updateStudentBySno(no, student);
            if (result) {
                if (adm.equals(request.getSession().getAttribute(flag))) {
                    out.write("adm_update");
                } else {
                    request.getSession().setAttribute("sname", name);
                    out.write("stu_update");
                }
            }
        } catch (NumberFormatException e) {
            out.write("false");
        }

    }
}
