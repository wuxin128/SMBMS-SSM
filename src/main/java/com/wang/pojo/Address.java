package com.wang.pojo;/*
@author carl
@date 2022/4/1 - 20:47
*/

import lombok.Data;

import java.sql.Date;

@Data
public class Address {

    private Integer id;
    private String contact;
    private String addressDesc;
    private String postCode;
    private String tel;
    private Integer createBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer userID;
}
