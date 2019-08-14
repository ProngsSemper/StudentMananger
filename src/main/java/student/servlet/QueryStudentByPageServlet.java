package student.servlet;

import student.entity.Page;
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

/**
 * @author Prongs
 */
@WebServlet("/QueryStudentByPageServlet")
public class QueryStudentByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adm = "administrator_login";
        String flag = "flag";
        IStudentService studentService = new StudentServiceImpl();
        int count = studentService.getTotalCount();
        Page page = new Page();
        String cPage = request.getParameter("currentPage");
        /*
        当管理员第一次进入时将当前页设置为0
        */
        if (cPage == null) {
            cPage = "0";
        }
        if (adm.equals(request.getSession().getAttribute(flag))) {
            int currentPage = Integer.parseInt(cPage);
            page.setCurrentPage(currentPage);
            int totalCount = studentService.getTotalCount();
            page.setTotalCount(totalCount);
            /*
            设置每页大小（每页只显示3个学生）
             */
            int pageSize = 3;
            page.setPageSize(pageSize);
            List<Student> students = studentService.queryStudentsByPage(currentPage, pageSize);
            System.out.println(students);
            System.out.println(count);
            /*
            将list放入page中，以便前端用get方法获取
             */
            page.setStudents(students);
            request.getSession().setAttribute("page", page);
            request.getRequestDispatcher("administrator.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
