package student.dao.impl;

import student.dao.IStudentDao;
import student.entity.Student;
import student.util.DBUtil;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Object[] params = {student.getSno(), student.getSname(), student.getSage(), student.getSaddress(), student.getSpassword(), null, student.getSnum(), student.getSgender()};
        String sql = "insert into student values(?,?,?,?,?,?,?,?) ";
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
        String sql = "update student set sname =?,sage=?,saddress=?,snum=?,sgender=? where sno=? ";
        Object[] params = {student.getSname(), student.getSage(), student.getSaddress(), student.getSnum(), student.getSgender(), sno};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public boolean isExist(String sname) {
        return queryStudentByName(sname) != null;
    }

    @Override
    public boolean isExist(int sno) {
        return queryStudentBySno(sno) != null;
    }

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
                String password = rs.getString("spassword");
                String img = rs.getString("simg");
                int num = rs.getInt("snum");
                String gender = rs.getString("sgender");
                student = new Student(no, name, age, address, password, img, num, gender);
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
                String password = rs.getString("spassword");
                String img = rs.getString("simg");
                int num = rs.getInt("snum");
                String gender = rs.getString("sgender");
                student = new Student(no, name, age, address, password, img, num, gender);
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
                Student student = new Student(rs.getInt("sno"), rs.getString("sname"), rs.getInt("sage"), rs.getString("saddress"), rs.getString("spassword"), rs.getString("simg"), rs.getInt("snum"), rs.getString("sgender"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> queryConditional(String sname, String saddress, String sgender) {
        List<Student> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = "select * from student where 1=1";
        if (sname != null && !("".equals(sname))) {
            sql = sql + " and sname like ?";
        }
        if (saddress != null && !("".equals(saddress))) {
            sql = sql + " and saddress like ?";
        }
        if (sgender != null && !("".equals(sgender))) {
            sql = sql + " and sgender like ?";
        }
        try {
            pstmt = DBUtil.getConnection().prepareStatement(sql);
            if (sname != null && !("".equals(sname))) {
                pstmt.setString(1, sname);
                if (saddress != null && !("".equals(saddress))) {
                    pstmt.setString(2, saddress);
                    if (sgender != null && !("".equals(sgender))) {
                        pstmt.setString(3, sgender);
                    }
                } else if (sgender != null && !("".equals(sgender))) {
                    pstmt.setString(2, sgender);
                }
            } else if (saddress != null && !("".equals(saddress))) {
                pstmt.setString(1, saddress);
                if (sgender != null && !("".equals(sgender))) {
                    pstmt.setString(2, sgender);
                }
            } else if (sgender != null && !("".equals(sgender))) {
                pstmt.setString(1, sgender);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student studentList = new Student();
                studentList.setSimg(rs.getString("simg"));
                studentList.setSno(rs.getInt("sno"));
                studentList.setSname(rs.getString("sname"));
                studentList.setSage(rs.getInt("sage"));
                studentList.setSaddress(rs.getString("saddress"));
                studentList.setSpassword(rs.getString("spassword"));
                studentList.setSimg(rs.getString("simg"));
                studentList.setSnum(rs.getInt("snum"));
                studentList.setSgender(rs.getString("sgender"));
                list.add(studentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            DBUtil.CloseAll(rs, pstmt, DBUtil.connection);
        }
        return list;
    }

    @Override
    public boolean updateStudentImg(int sno, Student student) {
        String sql = "update student set simg=? where sno=? ";
        Object[] params = {student.getSimg(), sno};
        return DBUtil.executeUpdate(sql, params);
    }
}
