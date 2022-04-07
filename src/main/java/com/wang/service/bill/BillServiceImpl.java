package com.wang.service.bill;/*
@author carl
@date 2022/4/2 - 16:16
*/

import com.wang.dao.bill.BillMapper;
import com.wang.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    public List<Bill> getBillList(Map map) {
        return billMapper.getBillList(map);
    }
}
