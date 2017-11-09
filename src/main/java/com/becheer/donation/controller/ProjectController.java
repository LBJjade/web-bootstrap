package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.model.extension.project.ListProjectTypeExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.becheer.donation.service.IProjectService;
import com.becheer.donation.service.IProjectTypeService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.RegExUtil;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.awt.image.PNGImageDecoder;

import javax.annotation.Resource;
import java.util.List;

/*
* ProjectController 项目控制器
* Creator : xiaokepu
* Date : 2017-09-21
*/

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    private IProjectService projectService;

    @Resource
    private IProjectTypeService projectTypeService;


    @GetMapping("")
    public String View(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return this.render("project");
    }

    @PostMapping("/type")
    @ResponseBody
    public ResponseDto GetProjectType(HttpServletRequest request){
        try {
            List<ListProjectTypeExtension> result = projectTypeService.GetProjectType();
            return new ResponseDto(200,Message.PROJECT_TYPE_GET_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProjectType", ex);
            return new ResponseDto(500,Message.SERVER_ERROR);
        }
    }

    @PostMapping("/select")
    @ResponseBody
    public String GetProject(HttpServletRequest request,@RequestParam long type,@RequestParam int limit){
        if (limit>50||limit<0){
            limit=10;
        }
        return null;
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request,@RequestParam int pageSize,@RequestParam int pageNum,@RequestParam long tid){
        if (pageSize>50||pageSize<1){
            pageSize=6;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try{
            PageInfo<ListProjectExtension> result = projectService.GetProjectList(pageNum,pageSize,tid);
            return new ResponseDto(200,Message.PROJECT_GET_LIST_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProject", ex);
            return new ResponseDto(500,Message.SERVER_ERROR);
        }
    }

    @PostMapping("/all")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request,@RequestParam long tid){
        try{
            List<ListProjectExtension> result = projectService.GetProjectList(tid);
            return new ResponseDto(200,Message.PROJECT_GET_LIST_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProject", ex);
            return new ResponseDto(500,Message.SERVER_ERROR);
        }
    }

    /**
     * 获取全部项目列表
     */
    @PostMapping(value = "/project")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request, @RequestParam int pageNum, @RequestParam int pageSize) {
        if (pageNum<=0){
            pageNum=1;
        }
        if (pageSize>50||pageSize<=0){
            pageSize=10;
        }
        return ResponseDto.GetResponse(200,"success",projectService.GetProjectList(pageNum,pageSize));
    }

    @PostMapping("/relative")
    @ResponseBody
    public ResponseDto GetRelativeProject(HttpServletRequest request,@RequestParam long tid,@RequestParam int nums,@RequestParam long pid){
        if (nums<1||nums>20){
            nums=8;
        }
        try{
            List<ListProjectExtension> result = projectService.GetRelationProject(tid,pid,nums);
            return new ResponseDto(200,Message.PROJECT_GET_LIST_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProject", ex);
            return new ResponseDto(500,Message.SERVER_ERROR);
        }
    }

    @GetMapping("/{pid}")
    public String View_Detail(HttpServletRequest request,@PathVariable String pid){
        request.setAttribute("config", fileConfig);
        try{
            long tempId=Long.valueOf(pid);
            ProjectDetailExtension result=projectService.GetProjectDetail(tempId);
            if (result==null) {
                return render_404();
            }else{
                request.setAttribute("project",result);
                return this.render("project_detail");
            }
        }catch(Exception ex){
            LOGGER.error("GetProject", ex);
            return render_404();
        }
    }
}
