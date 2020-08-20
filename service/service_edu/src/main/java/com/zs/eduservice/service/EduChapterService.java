package com.zs.eduservice.service;

import com.zs.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zs.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
public interface EduChapterService extends IService<EduChapter> {
    //根据课程id查询章节和小节信息
    List<ChapterVo> getChapterByCourseId(String courseId);

    boolean deleteChapterById(String chapterId);

    void removeChapterByCourseId(String courseId);

}
