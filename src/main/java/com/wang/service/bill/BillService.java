package com.wang.service.bill;/*
@author carl
@date 2022/4/2 - 16:14
*/

import com.wang.pojo.Bill;

import java.util.List;
import java.util.Map;

public interface BillService {

    List<Bill> getBillList(Map map);
}
