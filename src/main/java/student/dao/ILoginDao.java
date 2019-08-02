package student.dao;

import student.entity.Login;
import student.util.DBUtil;

import java.sql.*;

public interface ILoginDao {
    public int login(Login login);
}
