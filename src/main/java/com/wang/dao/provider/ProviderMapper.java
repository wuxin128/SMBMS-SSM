package com.wang.dao.provider;/*
@author carl
@date 2022/4/1 - 20:58
*/

import com.wang.pojo.Provider;

import java.util.List;
import java.util.Map;

public interface ProviderMapper {

    int getProviderCount(Map map);

    List<Provider> getProviderList(Map map);

}
