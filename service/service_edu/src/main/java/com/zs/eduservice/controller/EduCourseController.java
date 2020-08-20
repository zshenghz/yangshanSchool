package com.zs.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.commonuntils.R;
import com.zs.eduservice.entity.EduCourse;
import com.zs.eduservice.entity.EduTeacher;
import com.zs.eduservice.entity.vo.CourseInfoVo;
import com.zs.eduservice.entity.vo.CoursePublishVo;
import com.zs.eduservice.entity.vo.CourseQuery;
import com.zs.eduservice.entity.vo.TeacherQuery;
import com.zs.eduservice.service.EduCourseService;
import org.apache.poi.ddf.EscherDump;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后课程id，为了后面添加大纲使用
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.queryCourseInfo(courseId);

        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //编写一个接口根据课程id查询课程所有信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);

    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //4、条件查询分页的方法
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) CourseQuery courseQuery){
        //创建page对象
        Page<EduCourse> eduCoursePage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        //多条件组合查询（表示条件可能有，可能没有）
        //mp学过 动态sql

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();


        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(title)){
            //构建条件
            queryWrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)){
            queryWrapper.eq("status",status);
        }

        //排序
        queryWrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        eduCourseService.page(eduCoursePage, queryWrapper);
        long total = eduCoursePage.getTotal();
        List<EduCourse> records = eduCoursePage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }


    //根据课程id删除课程，课程中的小节章节描述全部都要删除
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }
}

