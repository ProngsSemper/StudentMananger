package student.dao.impl;

import student.dao.IStudentDao;
import student.entity.Student;
import student.util.DBUtil;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    /**
     * 根据学号增加学生
     *
     * @param student
     * @return
     */
    @Override
    public boolean addStudent(Student student) {
        Object[] params = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress()};
        String sql = "insert into student values(?,?,?,?) ";
        return DBUtil.executeUpdate(sql, params);

    }

    /**
     * 根据学号删除学生
     *
     * @param sno
     * @return
     */
    @Override
    public boolean deleteStudentBySno(int sno) {
        String sql = "delete from student where sno=?";
        Object[] params = {sno};
        return DBUtil.executeUpdate(sql, params);
    }

    /**
     * 修改学生信息
     *
     * @param sno
     * @param student
     * @return
     */
    @Override
    public boolean updateStudentBySno(int sno, Student student) {
        String sql = "update student set sname =?,sage=?,saddress=? where sno=? ";
        Object[] params = {student.getSname(), student.getSage(), student.getSaddress(), sno};
        return DBUtil.executeUpdate(sql, params);
    }

    /**
     * 判断学生是否存在
     *
     * @param sno
     * @return
     */
    @Override
    public boolean isExist(int sno) {
        return queryStudentBySno(sno) != null;
    }

    /**
     * 查询是否存在
     *
     * @param sno
     * @return
     */
    @Override
    public Student queryStudentBySno(int sno) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            String sql = "select * from student where sno =? ";
            pstmt = DBUtil.getConnection().prepareStatement(sql);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
            }
            return student;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                DBUtil.CloseAll(rs, pstmt, DBUtil.getConnection());
            } catch (SQLException | PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Student queryStudentByName(String sname) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            String sql = "select * from student where sname =? ";
            pstmt = DBUtil.getConnection().prepareStatement(sql);
            pstmt.setString(1, sname);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
            }
            return student;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                DBUtil.CloseAll(rs, pstmt, DBUtil.getConnection());
            } catch (SQLException | PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询所有学生
     *
     * @return
     */
    @Override
    public List<Student> queryAllStudents() {
        List<Student> students = new ArrayList<>();
        Student student = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from student";
            rs = DBUtil.executeQuery(sql, null);
            while (rs.next()) {
                int no = rs.getInt("sno");
                String name = rs.getString("sname");
                int age = rs.getInt("sage");
                String address = rs.getString("saddress");
                student = new Student(no, name, age, address);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.CloseAll(rs, pstmt, DBUtil.connection);
        }
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(1) from student";
        return DBUtil.getTotalCount(sql);
    }

    @Override
    public List<Student> queryStudentByPage(int currentPage, int pageSize) {
        String sql = "select * from student limit ?,?;";
        Object[] params = {currentPage * pageSize, pageSize};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        List<Student> students = new ArrayList<>();
        try {
            while (rs.next()) {
                Student student = new Student(rs.getInt("sno"), rs.getString("sname"), rs.getInt("sage"), rs.getString("saddress"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
