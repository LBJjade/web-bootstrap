package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.progress.ProgressExtension;
import com.becheer.donation.model.extension.project.MemberProjectDetailExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.service.IContractService;
import com.becheer.donation.service.IProgressService;
import com.becheer.donation.service.IProjectProgressService;
import com.becheer.donation.service.IProjectService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Resource
    IProgressService progressService;

    @Resource
    IContractService contractService;

    @Resource
    IProjectProgressService projectProgressService;

    @Access(authorities="member")
    @GetMapping("")
    public String View(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return this.render("home/project");
    }

    @Access(authorities="member")
    @GetMapping("/{contractProjectId}")
    public String GetProjectDetail(HttpServletRequest request,@PathVariable long contractProjectId){
        request.setAttribute("config", fileConfig);
        try {
            MemberContractDetailExtension contract =contractService.getContractByContractProjectId(contractProjectId);
            if (contract==null){
                render_404();
            }
            MemberProjectDetailExtension memberProjectDetailExtension=projectService.GetMemberProjectDetail(contractProjectId);

            if (memberProjectDetailExtension==null){
                return render_404();
            }else{
                request.setAttribute("project",memberProjectDetailExtension);
                return render("home/project_detail");
            }
        }catch(Exception ex){
            LOGGER.error("GetProjectDetail", ex);
            return render_500();
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        if (pageSize<1||pageSize>50){
            pageSize=5;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try {
            PageInfo<MemberProjectExtension> result=projectService.GetProjectList(currentMember.getMemberId(),pageNum,pageSize);
            return new ResponseDto(200, Message.PROJECT_GET_LIST_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProjectType", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


    @PostMapping("/progress")
    @ResponseBody
    public ResponseDto GetAllprogress(HttpServletRequest request, @RequestParam long contractProjectId){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        MemberContractDetailExtension contract =contractService.getContractByContractProjectId(contractProjectId);
        if (contract==null){
            return MemberAuthFailed();
        }
        try {
            List<ProgressExtension> result=progressService.GetAllProgress(contractProjectId,"dnt_contract_project");
            return new ResponseDto(200, Message.MEMBER_PROJECT_GET_PROGRESS_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetAllprogress", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/projectProgress")
    @ResponseBody
    public ResponseDto GetProjectProgress(HttpServletRequest request, @RequestParam long projectId, @RequestParam int pageSize, @RequestParam int pageNum){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
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
            LOGGER.error("GetProjectProgress", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
