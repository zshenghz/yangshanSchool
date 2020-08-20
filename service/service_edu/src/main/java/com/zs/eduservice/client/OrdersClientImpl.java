package com.zs.eduservice.client;

import com.zs.servicebase.exceptionhandler.GuliException;
import org.springframework.stereotype.Component;

/**
 * 熔断器
 *
 * @author zhousheng
 * @date 2020年 08月08日 21:09:45
 */
@Component
public class OrdersClientImpl implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {

        return false;
    }
}
