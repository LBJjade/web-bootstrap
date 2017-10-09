package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.service.IProjectService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* ProjectController
* Creator : xiaokepu
* Date : 2017-09-22
*/
@Controller
@RequestMapping("/home/project")
public class HomeProjectController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeProjectController.class);

    @Resource
    IProjectService projectService;

    @GetMapping("")
    public String View(HttpServletRequest request){
        return this.render("home/project");
    }

    @GetMapping("/{projectId}")
    public String Detail(HttpServletRequest request){
        return this.render("home/project_detail");
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum){
        if (pageSize<1||pageSize>50){
            pageSize=5;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try {
            MemberSessionExtension currentMember=GetCurrentUser(request);
            PageInfo<MemberProjectExtension> result=projectService.GetProjectList(currentMember.getMemberId(),pageNum,pageSize);
            return new ResponseDto(200, Message.PROJECT_GET_LIST_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProjectType", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
