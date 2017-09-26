package com.becheer.donation.controller;

/*
* ProgressController
* Creator : xiaokepu
* Date : 2017-09-25
*/

import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IProjectProgressService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/progress")
public class ProgressController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    IProjectProgressService  projectProgressService;

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetProjectProgress(HttpServletRequest request, @RequestParam long projectId,@RequestParam int pageSize,@RequestParam int pageNum){
        if (pageSize<1||pageSize>50){
            pageSize=5;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try {
            PageInfo<ProjectProgress> result=projectProgressService.GetProjectProgress(projectId,pageSize,pageNum);
            return new ResponseDto(200,Message.PROJECT_PROGRESS_GET_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProjectType", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
