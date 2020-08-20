package com.zs.eduservice.controller;

import com.zs.commonuntils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhousheng
 * @date 2020年 07月10日 12:29:59
 */
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin
public class EduLoginController {
    //login
    @RequestMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    //info
    @RequestMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
