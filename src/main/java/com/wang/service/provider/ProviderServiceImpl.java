package com.wang.service.provider;/*
@author carl
@date 2022/4/1 - 21:06
*/

import com.wang.dao.provider.ProviderMapper;
import com.wang.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl implements ProviderService{

    @Autowired
    private ProviderMapper providerMapper;

    public int getProviderCount(Map map) {
        return providerMapper.getProviderCount(map);
    }

    public List<Provider> getProviderList(Map map) {
        return providerMapper.getProviderList(map);
    }
}
