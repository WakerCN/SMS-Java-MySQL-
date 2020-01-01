package model.dao;

import model.vo.Student;
import util.DBManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: 学生类Dao
 *
 * @author Waker
 * @since 2019-12-30 周一 10:02
 */
public class StudentDao {

    public boolean add(Student stu) {
        int s_id = stu.getS_id();
        String s_name = stu.getS_name();
        int gender = stu.getGender();
        Date birthday = stu.getBirthday();
        String sql = "insert into student (s_id, s_name, gender, birthday)";
        sql += " values (" + s_id + ",'" +
                s_name + "'," +
                gender + ",'" +
                birthday + "')";

        System.out.println(sql);

        DBManager db = new DBManager();
        boolean ret = false;
        try {
            ret = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 返回根据s_id查询的结果数目
     */
    public int ret_qureyBySid(int s_id) throws SQLException {
        int count = 0;
        DBManager db = new DBManager();
        String sql = "select * from student " +
                "where s_id = " + s_id;
        ResultSet rs = null;
        try {
            rs = db.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            while (rs.next()) {
                count++;
            }
        }
        db.close();
        return count;
    }

    public ResultSet qurey_all() {
        ResultSet rs = null;
        String sql = "select s_id, s_name, gender, birthday from student";
        DBManager db = new DBManager();
        try {
            rs = db.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet qureyBySid(int s_id) {
        DBManager db = new DBManager();
        String sql = "select s_id, s_name, gender, birthday " +
                " from student " +
                " where s_id = " + s_id;
        ResultSet rs = null;
        try {
            rs = db.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 根据学生的姓名删除数据库中的记录
     * @param s_name
     */
    public boolean deleteBySname(String s_name) {
        boolean ret = false;
        String sql = "delete from student where " +
                "s_name = " + "'" + s_name + "'";
        DBManager db = new DBManager();
        try {
            ret = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean update(Student stu) {
        boolean ret = false;
        DBManager db = new DBManager();
        String sql = "update student set " +
                "s_id = " + stu.getS_id() + ", " +
                "s_name = " + "'" + stu.getS_name() + "', " +
                "gender = " + stu.getGender() + ", " +
                "birthday = " + "'" + stu.getBirthday() + "' " +
                "where s_id = " + stu.getS_id();
        System.out.println(sql);
        try {
            ret = db.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
