package com.zs.eduservice.client;

import com.zs.commonuntils.R;
import com.zs.commonuntils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用ucenter的方法
 *
 * @author zhousheng
 * @date 2020年 08月05日 11:48:41
 */
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class) //调用的服务名称和出错时执行的方法
@Component
public interface UcenterClient {
    //评论模块使用，根据用户id获取用户头像和昵称
    @GetMapping("/educenter/member/getMemberInfoByMemberid/{memberId}")
    public UcenterMemberVo getMemberInfoByMemberid(@PathVariable("memberId") String memberId);
}
