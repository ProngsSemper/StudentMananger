package student.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        //上传用户头像
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = iterator.next();
                    int no = (int) request.getSession().getAttribute("imgSno");
                        String fakePath = request.getSession().getServletContext().getRealPath("/");
                        String path = fakePath.replace("out\\artifacts\\StundentManager_war_exploded", "src\\main\\webapp\\upload");
                        String fileName = item.getName();
                        String ext = fileName.substring(fileName.indexOf(".") + 1);
                        if (!("png".equals(ext) || "gif".equals(ext) || "jpg".equals(ext))) {
                            request.setAttribute("error", "uploadError");
                            System.out.println("图片类型有误");
                            if ("student_login".equals(request.getSession().getAttribute("flag"))) {
                                request.getRequestDispatcher("student.jsp").forward(request, response);
                            } else if ("administrator_login".equals(request.getSession().getAttribute("flag"))) {
                                request.getRequestDispatcher("administrator.jsp").forward(request, response);
                            }
                            return;
                        }
                        File file = new File(path, fileName);
                        factory.setSizeThreshold(10240);
                        try {
                            //上传
                            item.write(file);
                            Student student = new Student(fileName);
                            IStudentService studentService = new StudentServiceImpl();
                            boolean result = studentService.updateStudentImg(no, student);
                            if (result) {
                                String adm = "administrator_login";
                                String flag = "flag";
                                if (adm.equals(request.getSession().getAttribute(flag))) {
                                    response.sendRedirect("QueryStudentByPageServlet");
                                } else {
                                    response.sendRedirect("QueryStudentByNameServlet");
                                }
                            }
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }

            } catch (FileUploadBase.FileSizeLimitExceededException e) {
                System.out.println("超过上传文件大小限制");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
