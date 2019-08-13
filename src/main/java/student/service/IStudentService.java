package student.service;

import student.entity.Student;

import java.util.List;

public interface IStudentService {
    Student queryStudentBySno(int sno);

    Student queryStudentByName(String name);

    boolean updateStudentBySno(int sno, String sname, Student student);

    boolean deleteStudentBySno(int sno);

    boolean addStudent(Student student);

    List<Student> queryStudentsByPage(int currentPage, int pageSize);

    List<Student> queryConditional(String sname, String saddress, String sgender);

    boolean updateStudentImg(int sno, Student student);

    int getTotalCount();
}
