package model.dao;

import model.vo.User;
import util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: User用户Dao
 *
 * @author Waker
 * @since 2019-12-28 周六 21:18
 */
public class UserDao {

    public boolean checkUser(User user) {
        boolean ret = false;
        List<User> userList = query(user);

        if (userList.size() > 0) {
            ret = true;
        }
        return ret;
    }

    private List<User> query(User user) {
        List<User> userList = new ArrayList<>();

        String sql = "select * from user where 1 = 1 ";
        if (user.getUserName() != null) {
            sql += " and userName = '" + user.getUserName() + "'";
        }
        if (user.getPassWord() != null) {
            sql += " and passWord = '" + user.getPassWord() + "'";
        }
        System.out.println("sql-----> " + sql);

        DBManager db = new DBManager();
        ResultSet rs = null;

        try {
            rs = db.executeQuery(sql);
            while (rs.next()) {
                User user_temp = new User();
                user_temp.setUserName(rs.getString("userName"));
                user_temp.setPassWord(rs.getString("passWord"));
                userList.add(user_temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.close();
        }
        return userList;
    }
}
