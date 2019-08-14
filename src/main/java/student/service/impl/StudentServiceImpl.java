package student.service.impl;

import student.dao.IStudentDao;
import student.dao.impl.StudentDaoImpl;
import student.entity.Student;
import student.service.IStudentService;

import java.util.List;

/**
 * @author Prongs
 */
public class StudentServiceImpl implements IStudentService {
    IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student queryStudentBySno(int sno) {
        return studentDao.queryStudentBySno(sno);
    }

    @Override
    public Student queryStudentByName(String name) {
        return studentDao.queryStudentByName(name);
    }

    @Override
    public boolean updateStudentBySno(int sno, Student student) {
        if (studentDao.isExist(sno)) {
            return studentDao.updateStudentBySno(sno, student);
        }
        return false;
    }

    @Override
    public boolean deleteStudentBySno(int sno) {
        if (studentDao.isExist(sno)) {
            return studentDao.deleteStudentBySno(sno);
        } else {
            return false;
        }
    }

    @Override
    public boolean addStudent(Student student) {
        /*
          注册/新增用户时学号和姓名不能与他人相同
         */
        if (!(studentDao.isExist(student.getSno()) || studentDao.isExist(student.getSname()))) {
            studentDao.addStudent(student);
            return true;
        } else {
            System.out.println("此人已存在！");
            return false;
        }
    }

    @Override
    public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
        return studentDao.queryStudentByPage(currentPage, pageSize);
    }

    @Override
    public List<Student> queryConditional(String sname, String saddress, String sgender) {
        return studentDao.queryConditional(sname, saddress, sgender);
    }

    @Override
    public boolean updateStudentImg(int sno, Student student) {
        if (studentDao.isExist(sno)) {
            return studentDao.updateStudentImg(sno, student);
        }
        return false;
    }

    @Override
    public int getTotalCount() {
        return studentDao.getTotalCount();
    }

}

