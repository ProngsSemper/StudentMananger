package student.servlet;

import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Prongs
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        int no = Integer.parseInt(request.getParameter("sno"));
        IStudentService studentService = new StudentServiceImpl();
        boolean result = studentService.deleteStudentBySno(no);
        if (result) {
            response.sendRedirect("QueryStudentByPageServlet");
        } else {
            response.getWriter().println("删除失败！");
        }
    }
}
