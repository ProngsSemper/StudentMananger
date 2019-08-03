package student.service;

import student.entity.Student;

import java.util.List;

public interface IStudentService {
    Student queryStudentBySno(int sno);

    Student queryStudentByName(String name);

    List<Student> queryAllStudents();

    boolean updateStudentBySno(int sno, Student student);

    boolean deleteStudentBySno(int sno);

    boolean addStudent(Student student);

    List<Student> queryStudentsByPage(int currentPage, int pageSize);

    int getTotalCount();
}
