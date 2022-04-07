package com.wang.controller;/*
@author carl
@date 2022/4/2 - 16:05
*/

import com.wang.pojo.Bill;
import com.wang.service.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** @noinspection Duplicates*/
@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("/jsp/bill.query")
    public String getBillList(HttpServletRequest request, Model model){

        String queryProductName = request.getParameter("queryProductName");
        String temp1 = request.getParameter("queryProviderId");
        String temp2 = request.getParameter("queryIsPayment");

        int queryIsPayment = 0;
        int queryProviderId = 0;
        if(queryProductName == null){
            queryProductName = "";
        }
        if(temp1 != null){
            queryProviderId = Integer.valueOf(temp1);
        }
        if(temp2 != null){
            queryIsPayment = Integer.valueOf(temp2);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productName","%"+queryProductName+"%");
        map.put("productId",queryProviderId);
        map.put("isPayment",queryIsPayment);

        List<Bill> billList = billService.getBillList(map);

        model.addAttribute(billList);

        return "forward:/jsp/billlist.jsp";
    }
}
