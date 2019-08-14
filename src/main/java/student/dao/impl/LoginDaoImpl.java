package student.dao.impl;

import student.dao.ILoginDao;
import student.entity.Login;
import student.util.DBUtil;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Prongs
 */
public class LoginDaoImpl implements ILoginDao {
    @Override
    public  int login(Login login) {
        int result = -1;
        ResultSet rs = null;
        try {
            String sql = "select count(*) from student where sname='" + login.getName() + "' and spassword='" + login.getPwd() + "'";
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
        }
        finally {
            try {
                DBUtil.closeAll(rs,DBUtil.pstmt,DBUtil.getConnection());
            } catch (SQLException | PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }
}
