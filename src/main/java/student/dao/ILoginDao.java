package student.dao;

import student.entity.Login;

/**
 * @author Prongs
 */
public interface ILoginDao {
    /**
     * 判断数据库内是否有该学生姓名及密码
     * 判断输入姓名密码是否正确
     *
     * @param login 当姓名密码都输入正确时login值为1 错误时值为0 发生异常时值为-1
     * @return 返回login的值
     */
    int login(Login login);
}
