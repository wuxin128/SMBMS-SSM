package com.wang.dao.bill;/*
@author carl
@date 2022/4/2 - 16:07
*/

import com.wang.pojo.Bill;

import java.util.List;
import java.util.Map;

public interface BillMapper {

    List<Bill> getBillList(Map map);
}
