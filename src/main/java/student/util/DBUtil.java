package student.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;

/**
 * @author Prongs
 */
public class DBUtil {
    private static Connection connection = null;
    public static PreparedStatement pstmt = null;
    private static ResultSet rs = null;
    private static ComboPooledDataSource pool = new ComboPooledDataSource();

    public static Connection getConnection() throws SQLException, PropertyVetoException {
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

    private static PreparedStatement creatPreparedStatement(String sql, Object[] params) throws SQLException, PropertyVetoException {
        pstmt = getConnection().prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    public static int getTotalCount(String sql) {
        int count = -1;
        try {
            pstmt = creatPreparedStatement(sql, null);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, connection);
        }
        return count;
    }

    public static void closeAll(ResultSet rs, Statement statement, Connection connection) {
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
            return count > 0;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll(null, pstmt, connection);
        }
    }

    public static ResultSet executeQuery(String sql, Object[] params) {
        try {
            pstmt = creatPreparedStatement(sql, params);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }

}
