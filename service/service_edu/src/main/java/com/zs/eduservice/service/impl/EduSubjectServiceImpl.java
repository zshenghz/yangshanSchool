package com.zs.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zs.eduservice.entity.EduSubject;
import com.zs.eduservice.entity.excel.SubjectData;
import com.zs.eduservice.entity.subject.OneSubject;
import com.zs.eduservice.entity.subject.TwoSubject;
import com.zs.eduservice.listener.SubjectExcelListener;
import com.zs.eduservice.mapper.EduSubjectMapper;
import com.zs.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zs.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-13
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //查询课程分类，并按照树型结构返回数据
    @Override
    public List<OneSubject> querySubjectList() {
        //从数据库中查询除一级分类数据
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", "0");
        List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);
        //查询二级分类
        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.ne("parent_id", "0");
        List<EduSubject> eduSubjects1 = baseMapper.selectList(queryWrapper1);
        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //遍历封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //封装到要求的list集合里面list<OneSubject> finalSubjectList
        for (int i = 0; i < eduSubjects.size(); i++) {
            //得到oneSubjectList每个eduSubject对象
            EduSubject eduSubject = eduSubjects.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);

            //在遍历一级分类的过程中遍历二级分类
            //创建一个list集合存放每个一级分类的二级分类
            List<TwoSubject> finaltwoSubjects = new ArrayList<>();
            for (int i1 = 0; i1 < eduSubjects1.size(); i1++) {
                //获取每个二级分类
                EduSubject twoeduSubject = eduSubjects1.get(i1);
                //判断二级分类parentid和一级分类id是否一样
                if (oneSubject.getId().equals(twoeduSubject.getParentId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoeduSubject, twoSubject);
                    finaltwoSubjects.add(twoSubject);
                }
            }
            oneSubject.setChildren(finaltwoSubjects);
            finalSubjectList.add(oneSubject);
        }
        return finalSubjectList;
    }

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20002, "添加课程分类失败");
        }
    }
}
