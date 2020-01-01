package model.vo;

import java.sql.Date;

/**
 * Description: 学生类vo
 *
 * @author Waker
 * @since 2019-12-30 周一 09:57
 */
public class Student {

    private int s_id;
    private String s_name;
    private int gender;
    private Date birthday;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
