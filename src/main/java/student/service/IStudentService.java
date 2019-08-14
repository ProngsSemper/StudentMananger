package student.service;

import student.entity.Student;

import java.util.List;

/**
 * @author Prongs
 */
public interface IStudentService {

    /**
     * 根据学生学号查询学生
     * @param sno 学生学号
     * @return 返回查询到的学生
     */
    Student queryStudentBySno(int sno);

    /**
     * 根据学生姓名查询学生
     * @param name 学生姓名
     * @return 返回查询到的学生
     */
    Student queryStudentByName(String name);

    /**
     * 根据学生学号更新学生信息
     * @param sno 根据学生学号查询学生是否存在
     * @param student 若学生存在 根据学号查询到学生后 获取该学生对象的所有信息
     * @return 返回布尔值在servlet中判断学生信息是否更新成功
     */
    boolean updateStudentBySno(int sno, Student student);

    /**
     * 根据学生学号删除学生
     * @param sno 根据学号查询学生是否存在
     * @return 返回布尔值在servlet中判断是否删除成功
     */
    boolean deleteStudentBySno(int sno);

    /**
     * 增加/注册学生
     * @param student 传入学生对象以写入数据库
     * @return 返回布尔值在servlet中判断是否增加/注册成功
     */
    boolean addStudent(Student student);

    /**
     * 分页显示学生
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 根据当前页和页面大小判断在该页显示的学生对象有多少有哪些并放入list
     */
    List<Student> queryStudentsByPage(int currentPage, int pageSize);

    /**
     * 模糊条件查询学生
     * @param sname 学生姓名
     * @param saddress 学生地址
     * @param sgender 学生性别
     * @return 根据条件查询到的学生对象放入list
     */
    List<Student> queryConditional(String sname, String saddress, String sgender);

    /**
     * 上传/更新学生头像
     * @param sno 根据学生学号查询学生是否存在
     * @param student 把图片地址放入学生对象中
     * @return 返回布尔值在servlet中判断是上传/更新头像成功
     */
    boolean updateStudentImg(int sno, Student student);

    /**
     * 获取总页数
     * @return 返回总页数
     */
    int getTotalCount();
}
