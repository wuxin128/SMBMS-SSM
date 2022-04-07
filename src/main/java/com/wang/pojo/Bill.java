package com.wang.pojo;/*
@author carl
@date 2022/4/1 - 20:50
*/

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Bill {
    private Integer id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private BigDecimal productCount;
    private BigDecimal totalPrice;
    private Integer isPayment;
    private Integer createdBy;
    private Date creationDate;
    private Integer modifyBy;
    private Date modifyDate;
    private Integer providerId;
    private String providerName;

}
