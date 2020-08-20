package com.zs.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhousheng
 * @date 2020年 07月12日 15:59:05
 */

public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
