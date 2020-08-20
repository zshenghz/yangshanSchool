package com.zs.eduservice.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.commonuntils.JwtUtils;
import com.zs.commonuntils.R;
import com.zs.eduservice.client.OrdersClient;
import com.zs.eduservice.entity.EduCourse;
import com.zs.eduservice.entity.frontvo.CourseFrontVo;
import com.zs.eduservice.entity.frontvo.CourseWebVo;
import com.zs.eduservice.entity.vo.ChapterVo;
import com.zs.eduservice.entity.vo.CourseInfoVo;
import com.zs.eduservice.entity.vo.CoursePublishVo;
import com.zs.eduservice.service.EduChapterService;
import com.zs.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
//@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterByCourseId(courseId);
        //根据课程id和用户id查询当前课程是否已经支付过了
        if (!StringUtils.isEmpty(JwtUtils.getMemberIdByJwtToken(request))){
            boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
            return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
        }else {
            boolean buyCourse = false;
            return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
        }
    }

    //编写一个接口根据课程id查询课程所有信息，远程调用
    @GetMapping("getCourseInfoById/{id}")
    public CourseInfoVo getCourseInfoById(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(coursePublishVo, courseInfoVo);
        return courseInfoVo;
    }
}












