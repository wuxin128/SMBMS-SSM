package com.wang.pojo;/*
@author carl
@date 2022/3/22 - 18:54
*/

import lombok.Data;

import java.sql.Date;
@Data
public class User {
    private Integer id;
    private String userCode;
    private String userName;
    private String userPassword;
    private Integer gender;
    private Date birthday;
    private String phone;
    private String address;
    private Integer userRole;  // 用户角色
    private Integer createdBy;  // 创建者
    private Date creationDate;  // 创建日期
    private Integer modifyBy; //更新者
    private Date modifyDate;
    private Integer age;
    private String userRoleName;  // 用户角色名称

    public Integer getAge(){
        Date date = new Date(System.currentTimeMillis());
        Integer age = date.getYear() - birthday.getYear();
        return age;
    }
}
