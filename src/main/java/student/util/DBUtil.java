package student.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import student.entity.Student;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbcdemo";
//    private static final String NAME = "root";
//    private static final String PASSWORD = "huxi913836";
    public static Connection connection = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    private static ComboPooledDataSource pool = new ComboPooledDataSource();

    public static Connection getConnection() throws ClassNotFoundException, SQLException, PropertyVetoException {
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection(URL, NAME, PASSWORD);
        pool.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jdbcdemo");
        pool.setUser("root");
        pool.setPassword("huxi913836");
        pool.setDriverClass("com.mysql.jdbc.Driver");
        pool.setInitialPoolSize(10);
        pool.setMaxIdleTime(30);
        pool.setMaxPoolSize(100);
        pool.setMinPoolSize(10);
        pool.setMaxStatements(200);
        return pool.getConnection();
    }

    public static PreparedStatement creatPreparedStatement(String sql, Object[] params) throws SQLException, ClassNotFoundException, PropertyVetoException {
        pstmt = getConnection().prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    public static int getTotalCount(String sql){
        int count = -1;
        try {
            pstmt = creatPreparedStatement(sql,null);
            rs = pstmt.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            CloseAll(rs,pstmt,connection);
        }
        return count;
    }

    public static void CloseAll(ResultSet rs, Statement statement, Connection connection) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean executeUpdate(String sql, Object[] params) {
        try {
            pstmt = creatPreparedStatement(sql, params);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseAll(null, pstmt, connection);
        }
    }

    public static ResultSet executeQuery(String sql, Object[] params) {
        List<Student> students = new ArrayList<>();
        Student student = null;
        try {
            pstmt = creatPreparedStatement(sql, params);
            rs = pstmt.executeQuery();
            return rs;
        } catch (ClassNotFoundException | SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
