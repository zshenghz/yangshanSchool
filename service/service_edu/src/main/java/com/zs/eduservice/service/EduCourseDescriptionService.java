package com.zs.eduservice.service;

import com.zs.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeDescriptionByCourseId(String courseId);
}
