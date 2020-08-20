package com.zs.eduorder.client;


import com.zs.commonuntils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //根据用户id获取用户信息
    @GetMapping("/educenter/member/getMemberInfoByMemberid/{memberId}")
    public UcenterMemberVo getMemberInfoByMemberid(@PathVariable("memberId") String memberId);
}
