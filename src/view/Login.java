package view;

import model.dao.UserDao;
import model.vo.User;

import java.util.Scanner;

/**
 * Description: 登录界面
 *
 * @author Waker
 * @since 2019-12-28 周六 21:07
 */
public class Login {

    private static String userName;
    private static String passWord;

    public static void main(String[] args) {
        System.out.println("** 学生管理系统 **");
        System.out.println("--->请登录");

        Scanner scanner = new Scanner(System.in);
        System.out.print("用户名: ");
        userName = scanner.next();
        System.out.print("密码: ");
        passWord = scanner.next();

        User user = new User(userName,passWord);
        UserDao userDao = new UserDao();
        if (!userDao.checkUser(user)) {
            System.out.println("Error: 用户名或密码错误，程序正在退出...");
            System.exit(0);
        } else {
            System.out.println("---> 登陆成功\n");
            new Main();
        }

    }

}
