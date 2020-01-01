package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Description: JDBC封装
 *
 * @author Waker
 * @since 2019-12-28 周六 21:29
 */
public class DBManager {

    private Connection con;
    private Statement stm;
    private ResultSet rs;
    private String dirverName;
    private String url;
    private String username;
    private String password;

    public DBManager() {
        FileInputStream is = null;
        try {
            is = new FileInputStream("E:/Code/Java/SMS/resources/config.properties");
            Properties prop = new Properties();
            prop.load(is);
            dirverName = prop.getProperty("driverName");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(dirverName);

            assert url != null;
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean executeUpdate(String sql) throws SQLException {
        boolean ret = false;
        int i = stm.executeUpdate(sql);
        if (i > 0) {
            ret = true;
        }
        return ret;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        rs = stm.executeQuery(sql);
        return rs;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
