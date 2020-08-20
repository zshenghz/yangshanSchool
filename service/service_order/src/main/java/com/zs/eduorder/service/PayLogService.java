package com.zs.eduorder.service;

import com.zs.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author zs
 * @since 2020-08-07
 */
public interface PayLogService extends IService<PayLog> {

    //生成微信支付二维码接口
    Map createNatvie(String orderNo);

    //根据订单号查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //向订单表添加记录，更新订单状态
    void updateOrdersStatus(Map<String, String> map);
}
