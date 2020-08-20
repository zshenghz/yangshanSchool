package com.zs.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.eduservice.entity.frontvo.CourseFrontVo;
import com.zs.eduservice.entity.frontvo.CourseWebVo;
import com.zs.eduservice.entity.vo.CourseInfoVo;
import com.zs.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo queryCourseInfo(String courseId);

    //修改课程基本信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);
//根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
