package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.becheer.donation.model.extension.appeal.AppealDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.progress.ProgressExtension;
import com.becheer.donation.model.extension.project.MemberProjectDetailExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.service.IAppealService;
import com.becheer.donation.service.IContractService;
import com.becheer.donation.service.IProgressService;
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
* HomeContractController
* Creator : xiaokepu
* Date : 2017-10-07
*/
@Controller
@RequestMapping("/home/appeal")
public class HomeAppealController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeAppealController.class);

    @Resource
    IAppealService appealService;

    @Resource
    IProgressService progressService;

    @Resource
    IProjectService projectService;

    @Access(authorities="member")
    @GetMapping("")
    public String View(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return this.render("/home/appeal");
    }

    @Access(authorities="member")
    @GetMapping("/add/{contractProjectId}")
    public String ViewDetail(HttpServletRequest request,@PathVariable long contractProjectId){
        request.setAttribute("config", fileConfig);
        try {
            MemberProjectDetailExtension memberProjectDetailExtension=projectService.GetMemberProjectDetail(contractProjectId);
            if (memberProjectDetailExtension==null){
                return render_404();
            }else{
                request.setAttribute("project",memberProjectDetailExtension);
                return this.render("/home/launch_detail");
            }
        }catch(Exception ex){
            LOGGER.error("ViewDetail", ex);
            return render_500();
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetAppealList(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum){
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
            PageInfo<MemberAppealExtension> result=appealService.GetMemberAppeal(currentMember.getMemberId(),pageNum,pageSize);
            return new ResponseDto(200, Message.MEMBER_GET_CONTRACT_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetAppealList", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities="member")
    @GetMapping(value = "/{appealId}")
    public String GetAppealDetail(HttpServletRequest request,@PathVariable long appealId) {
        request.setAttribute("config", fileConfig);
        try{
            MemberSessionExtension currentMember=GetCurrentUser(request);
            MemberAppealDetailExtension  memberAppealDetailExtension=appealService.GetMemberAppealDetail(appealId,currentMember.getMemberId());
            if (memberAppealDetailExtension==null){
                return render_404();
            }else{
                request.setAttribute("appeal",memberAppealDetailExtension);
                return render("home/appeal_detail");
            }
        }catch(Exception ex){
            LOGGER.error("GetAppealDetail", ex);
            return render_500();
        }
    }

    @PostMapping("/progress")
    @ResponseBody
    public ResponseDto GetAllProgress(HttpServletRequest request, @RequestParam long appealId){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        try {
            List<ProgressExtension> result=progressService.GetAllProgress(appealId,"dnt_appeal");
            return new ResponseDto(200, Message.MEMBER_APPEAL_PROGRESS_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetAllProgress", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


    @PostMapping("/add")
    @ResponseBody
    public  ResponseDto add(HttpServletRequest request,@RequestParam String title,@RequestParam String method,@RequestParam String content,@RequestParam long contractProjectId,@RequestParam long projectId){
        try {
            MemberSessionExtension currentMember=GetCurrentUser(request);
            if (currentMember==null){
                return MemberAuthFailed();
            }
            long memberId=currentMember.memberId;
            appealService.InsertAppeal(title,method,content,contractProjectId,projectId,memberId);
            return new ResponseDto(200, Message.SUBMIT_APPEAL_SUCCESS);
        }catch(Exception ex){
            return new ResponseDto(500, Message.SUBMIT_APPEAL_FAILED);
        }
    }



}
