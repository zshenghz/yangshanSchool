package com.zs.eduservice.controller;


import com.zs.commonuntils.R;
import com.zs.eduservice.entity.subject.OneSubject;
import com.zs.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-13
 */
@Api(description="课程分类管理")
////@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.saveSubject(file,eduSubjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    //课程分类列表（树形）
    @GetMapping("getSubjectList")
    public R getSubjectList(){
        List<OneSubject> oneSubjects = eduSubjectService.querySubjectList();
        return R.ok().data("list",oneSubjects);
    }
}

