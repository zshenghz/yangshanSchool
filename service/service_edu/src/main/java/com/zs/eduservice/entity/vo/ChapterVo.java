package com.zs.eduservice.entity.vo;

import com.zs.eduservice.entity.subject.TwoSubject;
import lombok.Data;

import java.util.List;

/**
 * 章节vule object
 *
 * @author zhousheng
 * @date 2020年 07月19日 14:19:19
 */
@Data
public class ChapterVo {
    private String id;
    private String title;

    private List<VideoVo> children;
}
