package view;

import controller.impl.StudentControllerImpl;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Description: 学生信息查询界面
 *
 * @author Waker
 * @since 2019-12-30 周一 11:51
 */
public class Query_student {

    public Query_student() {
        int chioce;
        StudentControllerImpl stu_control = new StudentControllerImpl();
        while (true) {
            System.out.println();
            System.out.println("** Query (Student) **");
            System.out.println("1.显示所有学生信息");
            System.out.println("2.根据学号查询");
            System.out.println("0.返回主菜单");
            System.out.print(">>> 请选择:");
            Scanner scan = new Scanner(System.in);
            chioce = scan.nextInt();
            switch (chioce) {
                /* 显示所有学生信息 */
                case 1:
                    try {
                        stu_control.student_print();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                /* 根据学号查询 */
                case 2:
                    try {
                        stu_control.student_queryBySid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                /* 返回上一级 */
                case 0:
                    return;
                default:
            }
        }

    }
}
