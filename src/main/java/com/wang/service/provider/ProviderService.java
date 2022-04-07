package com.wang.service.provider;/*
@author carl
@date 2022/4/1 - 21:04
*/

import com.wang.pojo.Provider;

import java.util.List;
import java.util.Map;

public interface ProviderService {
    int getProviderCount(Map map);
    List<Provider> getProviderList(Map map);
}
