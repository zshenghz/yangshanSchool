package com.zs.eduservice.controller;


import com.zs.commonuntils.R;
import com.zs.eduservice.client.VodClient;
import com.zs.eduservice.entity.EduChapter;
import com.zs.eduservice.entity.EduVideo;
import com.zs.eduservice.service.EduVideoService;
import com.zs.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }


    //根据小节id查询
    @GetMapping("getVideoInfo/{videoId}")
    public R getChapterInfo(@PathVariable String videoId) {
        EduVideo videoServiceById = videoService.getById(videoId);
        return R.ok().data("video",videoServiceById);
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateChapter(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return R.ok();
    }

    //删除小节，删除对应阿里云视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new GuliException(20001,"删除视频失败，熔断器...");
            }
        }
        //删除小节
        videoService.removeById(id);
        return R.ok();
    }


}
