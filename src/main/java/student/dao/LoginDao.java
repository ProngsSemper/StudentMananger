package student.dao;

import student.entity.Login;
import student.util.DBUtil;

import java.sql.*;

public class LoginDao {
    public static int login(Login login) {
        int result = -1;
        ResultSet rs = null;
        try {
            String sql = "select count(*) from login where uname='" + login.getName() + "' and upwd='" + login.getPwd() + "'";
            rs = DBUtil.executeQuery(sql,null);
            if (rs.next()) {
                result = rs.getInt(1);
            }
            if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.CloseAll(rs,DBUtil.pstmt,DBUtil.connection);
        }
    }
}
