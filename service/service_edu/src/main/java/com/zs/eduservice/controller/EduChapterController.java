package com.zs.eduservice.controller;


import com.zs.commonuntils.R;
import com.zs.eduservice.entity.EduChapter;
import com.zs.eduservice.entity.vo.ChapterVo;
import com.zs.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/chapter")
//@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //根据课程id查询所有章节和小节的数据
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterList(@PathVariable String courseId){
        List<ChapterVo> allChapterVideo = eduChapterService.getChapterByCourseId(courseId);
        return R.ok().data("allChapterVideo",allChapterVideo);
    }

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    //根据章节id删除章节
    //策略如果章节下面有小节不能删除，没有小节则执行删除
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapterById(chapterId);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

