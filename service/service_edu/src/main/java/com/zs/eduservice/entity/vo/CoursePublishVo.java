package com.zs.eduservice.entity.vo;

import lombok.Data;

/**
 * @author zhousheng
 * @date 2020年 07月21日 15:48:27
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
