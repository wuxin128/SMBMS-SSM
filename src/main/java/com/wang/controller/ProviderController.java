package com.wang.controller;/*
@author carl
@date 2022/4/1 - 20:40
*/

import com.wang.dao.bill.BillMapper;
import com.wang.dao.provider.ProviderMapper;
import com.wang.dao.user.UserMapper;
import com.wang.pojo.Bill;
import com.wang.pojo.Provider;
import com.wang.service.provider.ProviderService;
import com.wang.util.PageSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** @noinspection Duplicates*/
@Controller
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping("/jsp/provider.query")
    public String getProviderCount(HttpServletRequest request, Model model){

        String queryProCode = request.getParameter("queryProCode");
        String queryProName = request.getParameter("queryProName");
        String pageIndex = request.getParameter("pageIndex");
        int pageSize = 8;
        int currentPageNo = 1;

        if(queryProCode == null){
            queryProCode = "";
        }
        if(queryProName == null){
            queryProName = "";
        }
        if(pageIndex != null){
             currentPageNo = Integer.valueOf(pageIndex);
        }

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("proName","%"+queryProName+"%");
        map1.put("proCode","%"+queryProCode+"%");

        int totalCount = providerService.getProviderCount(map1);
        //通过工具类来获取页数支持
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        pageSupport.setTotalPageCount(pageSupport.setTotalPageCountByRs());

        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if (currentPageNo > pageSupport.getTotalPageCount()){
            currentPageNo = pageSupport.getTotalPageCount();
        }

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("proName","%"+queryProName+"%");
        map2.put("proCode","%"+queryProCode+"%");
        map2.put("currentPageNo",(currentPageNo-1)*pageSize);
        map2.put("pageSize",pageSize);

        List<Provider> providerList = providerService.getProviderList(map2);
        model.addAttribute("providerList",providerList);

        request.setAttribute("queryProName",queryProName);
        request.setAttribute("queryProCode",queryProCode);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("currentPageNo",currentPageNo);
        request.setAttribute("totalPageCount",pageSupport.getTotalPageCount());
        return "forward:/jsp/providerlist.jsp";
    }


    @Test
    public void test(){

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BillMapper mapper = context.getBean("billMapper", BillMapper.class);
        Map map = new HashMap();
        List<Bill> billList = mapper.getBillList(map);
        for(Bill bill : billList){
            System.out.println(bill);
        }
        System.out.println(mapper.getBillList(map));
    }
}
