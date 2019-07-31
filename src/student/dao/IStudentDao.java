package student.dao;

import student.entity.Student;

import java.util.List;

public interface IStudentDao {
    public boolean addStudent(Student student);

    public boolean deleteStudentBySno(int sno);

    public boolean updateStudentBySno(int sno, Student student);

    public boolean isExist(int sno);

    public Student queryStudentBySno(int sno);

    public List<Student> queryAllStudents();

    public int getTotalCount();

    public List<Student> queryStudentByPage(int currentPage,int pageSize);
}
