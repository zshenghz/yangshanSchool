package com.zs.educenter.service;

import com.zs.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author zs
 * @since 2020-08-01
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
