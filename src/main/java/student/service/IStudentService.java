package student.service;

import student.entity.Student;

import java.util.List;

public interface IStudentService {
    public Student queryStudentBySno(int sno);

    public List<Student> queryAllStudents();

    public boolean updateStudentBySno(int sno, Student student);

    public boolean deleteStudentBySno(int sno);

    public boolean addStudent(Student student);

    public List<Student> queryStudentsByPage(int currentPage,int pageSize);

    public int getTotalCount();
}
