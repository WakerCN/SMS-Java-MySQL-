package controller;

import model.vo.Student;

import java.sql.SQLException;

/**
 * Description: 学生信息控制类
 *
 * @author Waker
 * @since 2019-12-29 周日 16:59
 */
public interface StudentController {

    void student_add();
    void student_del();
    void student_update() throws SQLException;
    void student_query();

}
