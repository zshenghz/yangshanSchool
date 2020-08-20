package com.zs.msmservice.service;

import java.util.Map;

/**
 * @author zhousheng
 * @date 2020年 07月30日 21:01:15
 */

public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
