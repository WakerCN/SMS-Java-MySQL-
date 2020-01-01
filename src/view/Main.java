package view;

import controller.impl.StudentControllerImpl;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Description: 主界面
 *
 * @author Waker
 * @since 2019-12-29 周日 16:34
 */
public class Main {

    public Main() {
        int chioce;
        StudentControllerImpl stu_control = new StudentControllerImpl();
        while (true) {
            System.out.println();
            System.out.println("** SMS **");
            System.out.println("1.查询");
            System.out.println("2.添加");
            System.out.println("3.删除");
            System.out.println("4.修改");
            System.out.println("0.退出");
            System.out.print(">>> 请选择:");
            Scanner scan = new Scanner(System.in);
            chioce = scan.nextInt();
            switch (chioce) {
                /* 查询 */
                case 1:
                    stu_control.student_query();
                    break;

                /* 添加 */
                case 2:
                    stu_control.student_add();
                    break;

                /* 删除 */
                case 3:
                    stu_control.student_del();
                    break;

                /* 修改 */
                case 4:
                    try {
                        stu_control.student_update();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                /* 退出 */
                case 0:
                    System.out.println("---> 程序退出中...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("---> 选择有误，请重新输入.\n");
                    break;
            }
        }

    }

}
