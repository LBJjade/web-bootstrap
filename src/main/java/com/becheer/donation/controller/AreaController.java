package com.becheer.donation.controller;

import com.becheer.donation.model.Area;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IAreaService;
import com.becheer.donation.strings.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 包名: com.becheer.donation.controller
 * 文件说明: 区域控制器
 * 创建人:amber
 * 创建日期: 2017/12/12 17:33
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

    @Resource
    IAreaService areaService;

    @ResponseBody
    @GetMapping("/{pid}")
    public ResponseDto getArea(HttpServletRequest request, @PathVariable long pid){
        try{
            List<Area> result=areaService.selectAreaByParentId(pid);
            return new ResponseDto(200,Message.AREA_GET_SUCCESS,result);
        }catch (Exception ex){
            LOGGER.error("getArticleDetail", ex.getMessage());
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }
}
