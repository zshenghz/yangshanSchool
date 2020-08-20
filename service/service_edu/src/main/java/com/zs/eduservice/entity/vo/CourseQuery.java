package com.zs.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 封装课程条件查询的数据
 *
 * @author zhousheng
 * @date 2020年 07月22日 00:02:46
 */
@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
}
