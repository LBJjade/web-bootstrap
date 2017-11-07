package com.becheer.donation.utils;

/*
* OssUtil
* Creator : xiaokepu
* Date : 2017-10-25
*/

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.becheer.donation.configs.OssConfig;
import com.becheer.donation.configs.RedisConfig;
import com.becheer.donation.service.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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
    public static boolean addFileStream(InputStream inputStream,String key){
        try{
            ossClient.putObject(ossConfig.getBucketName(),key,inputStream);
            return true;
        }catch (OSSException oe){
            return false;
        }
    }


    /**
     * 上传本地磁盘上的文件
     * @param path 文件的物理路径
     * @param key 对象key
     * @return
     */
    public static boolean addFile(String path,String key){
        try {
            InputStream inputStream=new FileInputStream(path);
            if (inputStream.available()==0){
                return false;
            }
            return addFileStream(inputStream,key);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 上传byte数组
     * @param content
     * @param key
     * @return
     */
    public static boolean addByteArray(byte[] content,String key){
        try {
            ossClient.putObject(ossConfig.getBucketName(), key, new ByteArrayInputStream(content));
            return true;
        }catch(OSSException e){
            return false;
        }
    }
}
