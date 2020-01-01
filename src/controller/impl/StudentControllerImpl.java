package controller.impl;

import controller.StudentController;
import model.dao.StudentDao;
import model.vo.Student;
import view.Query_student;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description: 学生控制接口实现
 *
 * @author Waker
 * @since 2019-12-29 周日 23:05
 */
public class StudentControllerImpl implements StudentController {

    /**
     * 添加学生
     */
    @Override
    public void student_add() {
        Student stu = new Student();
        Scanner scan = new Scanner(System.in);

        // 给model.vo(stu)赋值
        System.out.println();
        System.out.println("** add **");
        System.out.print("学号:");
        stu.setS_id(scan.nextInt());
        System.out.print("姓名:");
        stu.setS_name(scan.next());
        System.out.print("性别:");
        stu.setGender(gender(scan.next()));
        // TODO 日期处理函数
        System.out.println("生日:");
        String birthday;
        birthday = scan.next();
        stu.setBirthday(Date.valueOf(birthday));

        StudentDao stu_Dao = new StudentDao();
        if (stu_Dao.add(stu)) {
            System.out.println("---> 成功插入一条记录");
        } else {
            System.out.println("---> 插入一条记录失败");
        }
    }

    /* 将输入的(性别字段)字符串转换成bool */
    private int gender(String sex) {
        int ret;
        switch (sex) {
            case "男":
            case "M":
                ret = 1;
                break;
            case "女":
            case "W":
                ret = 0;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sex);
        }
        return ret;
    }

    /**
     * 删除学生
     */
    @Override
    public void student_del() {
        System.out.println();
        System.out.println("** Delete (student) **");
        System.out.print(">>> 输入要删除学生的姓名:");
        Scanner scan = new Scanner(System.in);
        String s_name = scan.next();
        StudentDao stu_Dao = new StudentDao();
        boolean ret = stu_Dao.deleteBySname(s_name);
        if (ret) {
            System.out.println("---> 删除学生信息成功.");
        } else {
            System.out.println("---> 删除学生信息失败.");
        }
    }

    /**
     * 修改学生信息
     */
    @Override
    public void student_update() throws SQLException {
        System.out.println();
        System.out.println("** Update (student) **");
        System.out.print(">>> 输入要删除学生的学号:");
        Scanner scan = new Scanner(System.in);
        int s_id = scan.nextInt();
        StudentDao stu_dao = new StudentDao();

//        try {
//            int count = stu_dao.ret_qureyBySid(s_id);
//            if (count == 0) {
//                System.out.println("---> 没对应学号的学生，更新失败");
//            } else {
//                System.out.println("---> 正在更新对应的学生...");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        ResultSet rs = stu_dao.qureyBySid(s_id);
        Student stu = new Student();
        if (rs.next()) {
            stu.setS_id(rs.getInt(1));
            stu.setS_name(rs.getString(2));
            stu.setGender(rs.getInt(3));
            stu.setBirthday(rs.getDate(4));
            System.out.println("---> 要修改的学生信息如下:");
            System.out.println("==============================");
            System.out.println("学号  姓名 性别 生日");

            System.out.println( " " +
                    stu.getS_id() + "   " +
                    stu.getS_name() + "  " +
                    genderToString(stu.getGender()) + "  " +
                    stu.getBirthday());
        } else {
            System.out.println("---> 没有找到要更新的学生.");
            return;
        }

        // 获取新的更新数据
        System.out.println(">>> 请输入更新学生的信息(学号,姓名,性别,生日)");
        stu.setS_id(scan.nextInt());
        stu.setS_name(scan.next());
        stu.setGender(gender(scan.next()));
        stu.setBirthday(Date.valueOf(scan.next()));

        // 调用dao层更新数据库记录
        boolean ret = stu_dao.update(stu);
        if (ret) {
            System.out.println("---> 更新学生信息成功.");
        } else {
            System.out.println("---> 更新学生信息失败.");
        }
    }

    /**
     * 查询学生信息
     */
    @Override
    public void student_query() {
        new Query_student();
    }


    public void student_print() throws SQLException {
        List<Student> stuList = new ArrayList<>();
        StudentDao stu_Dao = new StudentDao();
        ResultSet rs = stu_Dao.qurey_all();

        while (rs.next()) {
            try {
                Student stu = new Student();
                stu.setS_id(rs.getInt(1));
                stu.setS_name(rs.getString(2));
                stu.setGender(rs.getInt(3));
                stu.setBirthday(rs.getDate(4));
                stuList.add(stu);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        System.out.println("==============================");
        System.out.println("学号  姓名 性别 生日");

        for (Student stu : stuList) {
        // TODO 格式化输出
        /*
            System.out.format("%5d%5s%5s%15s", stu.getS_id(),
                stu.getS_name()),
                genderToString(stu.getGender()),
                stu.getBirthday());
        */
            System.out.println( " " +
                    stu.getS_id() + "   " +
                    stu.getS_name() + "  " +
                    genderToString(stu.getGender()) + "  " +
                    stu.getBirthday());
        }
    }

    public String genderToString(int gender) {
        String gender_str = null;
        if (gender == 1) {
            gender_str = "男";
        } else if (gender == 0) {
            gender_str = "女";
        }
        return gender_str;
    }

    public void student_queryBySid() throws SQLException {
        Student stu = new Student();
        Scanner scan = new Scanner(System.in);
        System.out.print(">>> 请输入查询学生的学号:");
        stu.setS_id(scan.nextInt());
        StudentDao stu_dao = new StudentDao();
        ResultSet rs = stu_dao.qureyBySid(stu.getS_id());
        if (rs.next()) {
            stu.setS_name(rs.getString(2));
            stu.setGender(rs.getInt(3));
            stu.setBirthday(rs.getDate(4));

            System.out.println("==============================");
            System.out.println("学号  姓名 性别 生日");

            System.out.println( " " +
                    stu.getS_id() + "   " +
                    stu.getS_name() + "  " +
                    genderToString(stu.getGender()) + "  " +
                    stu.getBirthday());
        } else {
            System.out.println("---> 没有查询到任何结果.");
        }
    }
}
