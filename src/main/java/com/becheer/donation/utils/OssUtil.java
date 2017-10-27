package com.becheer.donation.utils;

/*
* OssUtil
* Creator : xiaokepu
* Date : 2017-10-25
*/

import com.aliyun.oss.OSSClient;
import com.becheer.donation.configs.OssConfig;
import com.becheer.donation.configs.RedisConfig;
import com.becheer.donation.service.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * OSS工具类
 */
public class OssUtil {
    private static OssConfig ossConfig=(OssConfig)SpringContextUtil.getBean("ossConfig");

    private static OSSClient ossClient=new OSSClient(ossConfig.getEndpoint(),ossConfig.getAccessKeyId(),ossConfig.getAccessKeySecret());;

    /**
     * 添加文件流
     * @param inputStream 文件流
     * @param key 对象名称
     */
    public static void AddFileStream(InputStream inputStream,String key){
        ossClient.putObject(ossConfig.getBucketName(),key,inputStream);
    }
}
