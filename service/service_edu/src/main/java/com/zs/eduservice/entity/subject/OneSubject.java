package com.zs.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

/**
 * 一级分类封装对象
 *
 * @author zhousheng
 * @date 2020年 07月17日 20:51:20
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    private List<TwoSubject> children;
}
