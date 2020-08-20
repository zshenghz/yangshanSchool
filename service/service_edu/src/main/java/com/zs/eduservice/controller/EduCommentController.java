package com.zs.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.commonuntils.JwtUtils;
import com.zs.commonuntils.R;
import com.zs.commonuntils.vo.UcenterMemberVo;
import com.zs.eduservice.client.UcenterClient;
import com.zs.eduservice.entity.EduComment;
import com.zs.eduservice.entity.EduTeacher;
import com.zs.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author zs
 * @since 2020-08-04
 */
@RestController
@RequestMapping("/eduservice/comment")
//@CrossOrigin
public class EduCommentController {

    @Autowired
    private EduCommentService eduCommentService;

    @Autowired
    private UcenterClient ucenterClient;

    //根据课程id分页查询课程评论的方法
    @GetMapping("pageCommont/{current}/{limit}")
    public R pageTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
            String courseId){
        Page<EduComment> eduCommentPage=new Page<EduComment>(current,limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseId)){
            //构建条件
            queryWrapper.eq("course_id", courseId);

        }
        eduCommentService.page(eduCommentPage, queryWrapper);
        List<EduComment> records = eduCommentPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", eduCommentPage.getCurrent());
        map.put("pages", eduCommentPage.getPages());
        map.put("size", eduCommentPage.getSize());
        map.put("total", eduCommentPage.getTotal());
        map.put("hasNext", eduCommentPage.hasNext());
        map.put("hasPrevious", eduCommentPage.hasPrevious());

        return R.ok().data(map);
    }

    //添加评论的方法
    @PostMapping("addComment")
    public R addComment(@RequestBody EduComment eduComment, HttpServletRequest httpServletRequest){
        String memberId = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        eduComment.setMemberId(memberId);
        UcenterMemberVo memberVo = ucenterClient.getMemberInfoByMemberid(memberId);
        eduComment.setNickname(memberVo.getNickname());
        eduComment.setAvatar(memberVo.getAvatar());
        eduCommentService.save(eduComment);
        return R.ok();
    }
}

