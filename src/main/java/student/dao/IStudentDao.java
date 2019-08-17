package student.dao;

import student.entity.Student;

import java.util.List;

/**
 * @author Prongs
 */
public interface IStudentDao {
    /**
     * 传入一个student并增加其信息进数据库
     *
     * @param student 传入学生对象
     * @return 执行sql语句和params语句将学生对象写入数据库
     */
    boolean addStudent(Student student);

    /**
     * 根据学生学号删除学生
     *
     * @param sno 传入学生学号对学生进行查询
     * @return 执行sql语句和params语句将学生删除
     */
    boolean deleteStudentBySno(int sno);

    /**
     * 根据学生学号删除学生
     *
     * @param sno     传入学生学号对学生进行查询
     * @param student 将修改后的学生信息放入params语句
     * @return 执行sql语句和params语句更新学生信息
     */
    boolean updateStudentBySno(int sno, Student student);

    /**
     * 根据学生学号查询学生是否存在
     *
     * @param sno 传入学生学号进行查询
     * @return 调用queryStudentBySno进行查询
     */
    boolean isExist(int sno);

    /**
     * 根据学生姓名查询学生是否存在
     *
     * @param sname 传入学生姓名进行查询
     * @return 调用queryStudentByName进行查询
     */
    boolean isExist(String sname);

    /**
     * 根据学生学号查询学生
     *
     * @param sno 传入学号进行查询
     * @return 根据学号查询后若学生存在将学生信息放入student对象后返回
     */
    Student queryStudentBySno(int sno);

    /**
     * 根据学生姓名查询学生
     *
     * @param sname 传入姓名进行查询
     * @return 根据姓名查询后若学生存在将学生信息放入student对象后返回
     */
    Student queryStudentByName(String sname);

    /**
     * 得到总页数
     *
     * @return 执行sql语句后查询到总页数
     */
    int getTotalCount();

    /**
     * 分页显示学生
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @return 根据当前页和页面大小判断放入多少学生对象到list里面 然后再将其返回得到此页面所需的学生对象
     */
    List<Student> queryStudentByPage(int currentPage, int pageSize);

    /**
     * 根据条件模糊筛选学生
     *
     * @param sname    数据库中相似的姓名
     * @param saddress 数据库中相似的地址
     * @param sgender  数据库中相似的性别
     * @return 返回根据条件筛选到的学生对象
     */
    List<Student> queryConditional(String sname, String saddress, String sgender);

    /**
     * 上传/更新学生头像
     *
     * @param sno     根据学生学号查询学生
     * @param student 查询数据库中学生头像的文件名
     * @return 执行sql与params语句更新学生头像
     */
    boolean updateStudentImg(int sno, Student student);
}
