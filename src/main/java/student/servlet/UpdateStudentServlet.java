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
        request.getParameter("sno");
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
            String adm = "administrator_login";
            String flag = "flag";
            if (adm.equals(request.getSession().getAttribute(flag))) {
                response.sendRedirect("QueryStudentByPageServlet");
            } else {
                //令QueryStudentByNameServlet重新获取学生名字达到更新目的
                request.getSession().setAttribute("sname", name);
                response.sendRedirect("QueryStudentByNameServlet");
            }
        } else {
            response.getWriter().println("修改失败！");
        }

    }
}
