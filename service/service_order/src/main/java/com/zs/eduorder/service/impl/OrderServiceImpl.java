package com.zs.eduorder.service.impl;

import com.zs.commonuntils.vo.CourseInfoVo;
import com.zs.commonuntils.vo.UcenterMemberVo;
import com.zs.eduorder.client.EduClient;
import com.zs.eduorder.client.UcenterClient;
import com.zs.eduorder.entity.Order;
import com.zs.eduorder.mapper.OrderMapper;
import com.zs.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zs.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zs
 * @since 2020-08-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    //1 生成订单的方法
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberVo memberInfoByMemberid = ucenterClient.getMemberInfoByMemberid(memberId);

        //通过远程调用根据课程id获取课信息
        CourseInfoVo courseInfoById = eduClient.getCourseInfoById(courseId);

        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoById.getTitle());
        order.setCourseCover(courseInfoById.getCover());
        order.setTeacherName(courseInfoById.getTeacherName());
        order.setTotalFee(courseInfoById.getPrice());
        order.setMemberId(memberId);
        order.setMobile(memberInfoByMemberid.getMobile());
        order.setNickname(memberInfoByMemberid.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
