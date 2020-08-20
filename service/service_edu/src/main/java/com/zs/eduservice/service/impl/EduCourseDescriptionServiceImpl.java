package com.zs.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zs.eduservice.entity.EduCourseDescription;
import com.zs.eduservice.mapper.EduCourseDescriptionMapper;
import com.zs.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeDescriptionByCourseId(String courseId) {
        QueryWrapper<EduCourseDescription> eduCourseDescriptionQueryWrapper = new QueryWrapper<>();
        eduCourseDescriptionQueryWrapper.eq("id", courseId);
        baseMapper.delete(eduCourseDescriptionQueryWrapper);
    }
}
