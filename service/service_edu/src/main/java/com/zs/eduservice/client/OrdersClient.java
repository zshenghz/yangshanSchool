package com.zs.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用order模块中的方法
 *
 * @author zhousheng
 * @date 2020年 08月08日 20:44:58
 */
@Component
@FeignClient(name ="service-order",fallback =OrdersClientImpl.class)
public interface OrdersClient {

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}