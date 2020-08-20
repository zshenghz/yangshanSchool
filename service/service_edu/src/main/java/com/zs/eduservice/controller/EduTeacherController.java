package com.zs.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zs.commonuntils.R;
import com.zs.eduservice.entity.EduTeacher;
import com.zs.eduservice.entity.vo.TeacherQuery;
import com.zs.eduservice.service.EduTeacherService;
import com.zs.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-30
 */
@RestController
@Api(description="讲师管理")
@RequestMapping("/eduservice/teacher")
//@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询讲师的方法
    @GetMapping("Teacher/{current}/{limit}")
    public R pageTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit){
        Page<EduTeacher> eduTeacherPage=new Page<EduTeacher>(current,limit);
        IPage<EduTeacher> page1 = teacherService.page(eduTeacherPage, null);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();


        return R.ok().data("total",total).data("rows",records);
    }

    //4、条件查询分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //多条件组合查询（表示条件可能有，可能没有）
        //mp学过 动态sql

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)){
            //构建条件
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create", end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        IPage<EduTeacher> page1 = teacherService.page(eduTeacherPage, queryWrapper);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }

    }
}