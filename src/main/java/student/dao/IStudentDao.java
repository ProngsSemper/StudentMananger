package student.dao;

import student.entity.Student;

import java.util.List;

public interface IStudentDao {
    boolean addStudent(Student student);

    boolean deleteStudentBySno(int sno);

    boolean updateStudentBySno(int sno, Student student);

    boolean isExist(int sno);

    Student queryStudentBySno(int sno);

    List<Student> queryAllStudents();

    int getTotalCount();

    List<Student> queryStudentByPage(int currentPage, int pageSize);
}
