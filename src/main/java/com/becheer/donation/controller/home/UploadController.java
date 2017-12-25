package com.becheer.donation.controller.home;

import com.becheer.donation.configs.OssConfig;
import com.becheer.donation.controller.BaseController;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * 包名: com.becheer.donation.controller.home
 * 文件说明: 文件上传控制器
 * 创建人:amber
 * 创建日期: 2017/12/25 09:50
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
@Controller
@RequestMapping("/home/upload")
public class UploadController extends BaseController {

    @Autowired
    OssConfig ossConfig;


    @GetMapping(value = "")
    public String uploader(){
        return this.render("/home/imguploader");
    }

    @ResponseBody
    @RequestMapping("/submit")
    public ResponseDto fileUpload(@RequestParam("file") MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response) {
        String fileName = UUID.randomUUID().toString();
        InputStream inputStream = null;
        try {
            inputStream = fileUpload.getInputStream();
            String originalFilename = fileUpload.getOriginalFilename();
            fileName += originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = ossConfig.getAttachPath() + fileName;
            boolean result = OssUtil.addFileStream(inputStream, fileName);
            inputStream.close();
            if (result) {
                return new ResponseDto(200, Message.FILE_UPLOAD_SUCCESS, fileName);
            } else {
                return new ResponseDto(400, Message.FILE_UPLOAD_FAILED);
            }
        } catch (Exception e) {
            return new ResponseDto(500,Message.SERVER_ERROR);
        }
    }

}
