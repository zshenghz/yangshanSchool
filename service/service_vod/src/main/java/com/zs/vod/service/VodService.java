package com.zs.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zhousheng
 * @date 2020年 07月22日 22:07:20
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}
