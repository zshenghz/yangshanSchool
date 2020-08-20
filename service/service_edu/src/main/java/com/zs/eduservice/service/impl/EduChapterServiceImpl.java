package com.zs.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zs.eduservice.entity.EduChapter;
import com.zs.eduservice.entity.EduVideo;
import com.zs.eduservice.entity.vo.ChapterVo;
import com.zs.eduservice.entity.vo.VideoVo;
import com.zs.eduservice.mapper.EduChapterMapper;
import com.zs.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zs.eduservice.service.EduVideoService;
import com.zs.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    //根据课程id查询章节和小节信息
    @Override
    public List<ChapterVo> getChapterByCourseId(String courseId) {
        //找到所有章节数据
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(chapterQueryWrapper);
        //找到所有courseId==this.courseId的小节数据
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(videoQueryWrapper);


        //遍历所有章节数据，存放到chapterVo类集合中
        List<ChapterVo> chapterVoList = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            ChapterVo chapterVo = new ChapterVo();
            EduChapter eduChapter = eduChapters.get(i);
            BeanUtils.copyProperties(eduChapter, chapterVo);


            //创建一个存储eduvideo的集合
            List<VideoVo> videoVoList = new ArrayList<>();
            //在遍历章节数据中遍历小节数据，在这些数据中找出与章节id匹配的小节，加入到当前章节的children中
            for (int i1 = 0; i1 < eduVideos.size(); i1++) {
                EduVideo eduVideo = eduVideos.get(i1);
                if (eduVideo.getChapterId().equals(chapterVo.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }

    //根据章节id删除章节
    @Override
    public boolean deleteChapterById(String chapterId) {
        //查询传进来的章节id有没有小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(videoQueryWrapper);
        if (count>0){
            //不执行删除
            throw new GuliException(20001,"无法删除，请先删除小节");
        }
        int i = baseMapper.deleteById(chapterId);
        return i>0;
    }
    //根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        baseMapper.delete(chapterQueryWrapper);
    }

}
