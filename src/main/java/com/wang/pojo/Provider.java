package com.wang.pojo;/*
@author carl
@date 2022/4/1 - 20:44
*/

import lombok.Data;

import java.sql.Date;

@Data
public class Provider {
    private Integer id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String proPhone;
    private String proFax;
    private String proAddress;
    private Integer createdBy;
    private Date creationDate;
    private Date modifyDate;
    private Integer modifyBy;

}
