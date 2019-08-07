package student.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
                    //动态获取文件路径
//                    String path = request.getSession().getServletContext().getRealPath("upload");
                    String path = "E:\\StudentManager\\src\\main\\webapp\\upload";
                    String fileName = item.getName();
                    String ext = fileName.substring(fileName.indexOf(".") + 1);
                    if (!("png".equals(ext) || "gif".equals(ext) || "jpg".equals(ext))) {
                        System.out.println("图片类型有误");
                        if ("student_login".equals(request.getSession().getAttribute("flag"))) {
                            request.getRequestDispatcher("student.jsp").forward(request, response);
                        }else if ("administrator_login".equals(request.getSession().getAttribute("flag"))){
                            request.getRequestDispatcher("administrator.jsp").forward(request, response);
                        }
                        return;
                    }
                    File file = new File(path, fileName);
                    factory.setSizeThreshold(10240);
                    //控制文件大小在500kb
                    upload.setSizeMax(512000);
                    try {
                        //上传
                        item.write(file);
                        request.getSession().setAttribute("img",fileName);
                        if ("student_login".equals(request.getSession().getAttribute("flag"))) {
                            request.getRequestDispatcher("student.jsp").forward(request, response);
                        }else if ("administrator_login".equals(request.getSession().getAttribute("flag"))){
                            request.getRequestDispatcher("administrator.jsp").forward(request, response);
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
