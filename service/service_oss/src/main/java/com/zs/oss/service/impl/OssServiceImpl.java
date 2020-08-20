package com.zs.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zs.oss.service.OssService;
import com.zs.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhousheng
 * @date 2020年 07月12日 15:58:23
 */
@Service
public class OssServiceImpl implements OssService {
    //上传头像到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 第一步使用工具类获取值
        String endpoint = ConstantPropertiesUtil.END_POIND;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            // 第二步创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1、在文件名称里面添加随机唯一的值  replaceAll("-", "")可以将原来uuid中的-替换为空
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName=uuid+fileName;

            //2、把文件按照日期进行分类
            //获取当前日期
            //2020/7/12
            //DateTime()是org.joda.time.DateTime中的
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //效果：2020/7/12/dfdfs.jpg
            fileName=dataPath+"/"+fileName;

            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径和文件名称   /aa/bb/1.jpg
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://zsstudy-file.oss-cn-hangzhou.aliyuncs.com/avatar/jng.jpg
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
