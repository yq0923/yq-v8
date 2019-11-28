package com.fh.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("t_shop_user")
public class User {
    private Integer id;
    private String userName;
    private String realUserName;//真实姓名
    private String passWord;
    private String salt;
    @TableField(exist = false)
    private Integer rememberMe ;//记住我
    private Integer sex;
    private Integer salary;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;
    private String phone;

    private String email;
    private Integer area1;
    private Integer area2;
    private Integer area3;
    private Integer area4;
    private String areaName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", realUserName='" + realUserName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", salt='" + salt + '\'' +
                ", rememberMe=" + rememberMe +
                ", sex=" + sex +
                ", salary=" + salary +
                ", age=" + age +
                ", joinTime=" + joinTime +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", area1=" + area1 +
                ", area2=" + area2 +
                ", area3=" + area3 +
                ", area4=" + area4 +
                ", areaName='" + areaName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealUserName() {
        return realUserName;
    }

    public void setRealUserName(String realUserName) {
        this.realUserName = realUserName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Integer rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getArea1() {
        return area1;
    }

    public void setArea1(Integer area1) {
        this.area1 = area1;
    }

    public Integer getArea2() {
        return area2;
    }

    public void setArea2(Integer area2) {
        this.area2 = area2;
    }

    public Integer getArea3() {
        return area3;
    }

    public void setArea3(Integer area3) {
        this.area3 = area3;
    }

    public Integer getArea4() {
        return area4;
    }

    public void setArea4(Integer area4) {
        this.area4 = area4;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
