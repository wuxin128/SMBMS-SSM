package com.wang.pojo;/*
@author carl
@date 2022/3/24 - 8:30
*/

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;
    private String roleCode;
    private String roleName;
    private Integer createBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
}
