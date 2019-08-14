package student.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Prongs
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //上传用户头像
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    String fakePath = request.getSession().getServletContext().getRealPath("/");
                    String path = fakePath.replace("out\\artifacts\\StundentManager_war_exploded", "src\\main\\webapp\\upload");
                    String fileName = item.getName();
                    String ext = fileName.substring(fileName.indexOf(".") + 1);
                    //判断文件类型
                    if (!("png".equals(ext) || "gif".equals(ext) || "jpg".equals(ext))) {
                        System.out.println("图片类型有误");
                        out.write("uploadError");
                        return;
                    }
                    File file = new File(path, fileName);
                    factory.setSizeThreshold(10240);
                    try {
                        //上传
                        int no = (int) request.getSession().getAttribute("imgSno");
                        item.write(file);
                        Student student = new Student(fileName);
                        IStudentService studentService = new StudentServiceImpl();
                        boolean result = studentService.updateStudentImg(no, student);
                        if (result) {
                            String adm = "administrator_login";
                            String flag = "flag";
                            if (adm.equals(request.getSession().getAttribute(flag))) {
                                out.write("adm_success");
                            } else {
                                out.write("stu_success");
                            }
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
