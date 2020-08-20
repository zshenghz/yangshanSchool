package com.zs.eduorder.client;


import com.zs.commonuntils.vo.CourseInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-edu")
public interface EduClient {

    //根据课程id查询课程信息
    @GetMapping("/eduservice/course/getCourseInfoById/{id}")
    public CourseInfoVo getCourseInfoById(@PathVariable("id") String id);

}
