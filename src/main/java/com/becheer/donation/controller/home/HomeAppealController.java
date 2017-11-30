package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.becheer.donation.model.extension.appeal.AppealDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.becheer.donation.model.extension.intention.IntentionExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.progress.ProgressExtension;
import com.becheer.donation.model.extension.project.MemberProjectDetailExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.service.IAppealService;
import com.becheer.donation.service.IContractService;
import com.becheer.donation.service.IProgressService;
import com.becheer.donation.service.IProjectService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import com.becheer.donation.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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

    @Access(authorities = {Role.PERSON, Role.COMPANY, Role.ACCEPTER})
    @GetMapping("")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("/home/appeal");
    }

    @Access(authorities = {Role.PERSON, Role.COMPANY, Role.ACCEPTER})
    @GetMapping("/add/{contractProjectId}")
    public String ViewDetail(HttpServletRequest request, @PathVariable long contractProjectId) {
        request.setAttribute("config", fileConfig);
        try {
            MemberProjectDetailExtension memberProjectDetailExtension = projectService.GetMemberProjectDetail(contractProjectId);
            if (memberProjectDetailExtension == null) {
                return render_404();
            } else if (memberProjectDetailExtension.getMemberId() != GetCurrentUser(request).getMemberId()) {
                return render_404();
            } else {
                request.setAttribute("project", memberProjectDetailExtension);
                return this.render("/home/appeal_edit");
            }
        } catch (Exception ex) {
            LOGGER.error("ViewDetail", ex.getMessage());
            return render_404();
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetAppealList(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        if (pageSize < 1 || pageSize > 50) {
            pageSize = 5;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        try {
            PageInfo<MemberAppealExtension> result = appealService.GetMemberAppeal(currentMember.getMemberId(), pageNum, pageSize);
            return new ResponseDto(200, Message.MEMBER_GET_CONTRACT_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("GetAppealList", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = {Role.PERSON, Role.COMPANY, Role.ACCEPTER})
    @GetMapping(value = "/{appealId}")
    public String GetAppealDetail(HttpServletRequest request, @PathVariable long appealId) {
        request.setAttribute("config", fileConfig);
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            MemberAppealDetailExtension memberAppealDetailExtension = appealService.GetMemberAppealDetail(appealId, currentMember.getMemberId());
            if (memberAppealDetailExtension == null) {
                return render_404();
            } else {
                request.setAttribute("appeal", memberAppealDetailExtension);
                return render("home/appeal_detail");
            }
        } catch (Exception ex) {
            LOGGER.error("GetAppealDetail", ex.getMessage());
            return render_404();
        }
    }

    @PostMapping("/progress")
    @ResponseBody
    public ResponseDto GetAllProgress(HttpServletRequest request, @RequestParam long appealId) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        try {
            MemberAppealDetailExtension appeal = appealService.GetMemberAppealDetail(appealId, currentMember.getMemberId());
            if (appeal == null) {
                return MemberAuthFailed();
            }
            List<ProgressExtension> result = progressService.GetAllProgress(appealId, "dnt_appeal");
            return new ResponseDto(200, Message.MEMBER_APPEAL_PROGRESS_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("GetAllProgress", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


    @PostMapping("/add")
    @ResponseBody
    public ResponseDto add(HttpServletRequest request, @RequestParam String title, @RequestParam String method, @RequestParam String content, @RequestParam long contractProjectId, @RequestParam long projectId) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            if (StringUtil.isNull(title)) {
                return new ResponseDto(400, Message.SUBMIT_APPEAL_TITLE_NULL);
            }
            if (StringUtil.isNull(method)) {
                return new ResponseDto(400, Message.SUBMIT_APPEAL_METHOD_NULL);
            }
            if (StringUtil.isNull(content)) {
                return new ResponseDto(400, Message.SUBMIT_APPEAL_CONTENT_NULL);
            }
            if (contractProjectId == 0 || projectId == 0) {
                return new ResponseDto(400, Message.SUBMIT_APPEAL_ID_NULL);
            }
            //TODO 还需要检查合同是否属于该会员，合同项目是否属于该会员
            long memberId = currentMember.memberId;
            appealService.InsertAppeal(title, method, content, contractProjectId, projectId, memberId);
            return new ResponseDto(200, Message.SUBMIT_APPEAL_SUCCESS);
        } catch (Exception ex) {
            LOGGER.error("add", ex.getMessage());
            return new ResponseDto(500, Message.SUBMIT_APPEAL_FAILED);
        }
    }

    @PostMapping("/addProgress")
    @ResponseBody
    public ResponseDto AddProgress(HttpServletRequest request, @RequestParam String content, @RequestParam long appealId) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        try {
            if (StringUtil.isNull(content)) {
                return new ResponseDto(200, Message.MEMBER_APPEAL_PROGRESS_CONTENT_NULL);
            }
            MemberAppealDetailExtension appeal = appealService.GetMemberAppealDetail(appealId, currentMember.getMemberId());
            if (appeal == null) {
                return MemberAuthFailed();
            }
            String title = content;
            if (title.length() > 30) {
                title = title.substring(0, 30);
            }
            long result = progressService.AddProgress(title, content, "dnt_appeal", appealId, currentMember.getMemberId(), 1);
            if (result > 0) {
                //已驳回的申述,如果用户继续提交资料,则状态变为处理中
                if (appeal.getStatus() == 1) {
                    appealService.UpdateAppealStatus(appealId, 2);
                }
                return new ResponseDto(200, Message.MEMBER_APPEAL_PROGRESS_ADD_SUCCESS, result);
            } else {
                return new ResponseDto(400, Message.MEMBER_APPEAL_PROGRESS_ADD_FAILED);
            }
        } catch (Exception ex) {
            LOGGER.error("AddProgress", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/solve")
    @ResponseBody
    public ResponseDto solveProgress(HttpServletRequest request, @RequestParam long appealId) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        try {
            MemberAppealDetailExtension appeal = appealService.GetMemberAppealDetail(appealId, currentMember.getMemberId());
            if (appeal == null) {
                return MemberAuthFailed();
            }
            if (appeal.getStatus() == 3 || appeal.getStatus() == 4) {
                return new ResponseDto(400, Message.MEMBER_APPEAL_STATUS_ERROR);
            }
            //解决
            progressService.AddProgress("申诉已解决", "申诉已解决", "dnt_appeal", appealId, currentMember.getMemberId(), 1);
            return appealService.UpdateAppealStatus(appealId, 3);
        } catch (Exception ex) {
            LOGGER.error("solveProgress", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/withdraw")
    @ResponseBody
    public ResponseDto withdrawProgress(HttpServletRequest request, @RequestParam long appealId) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        try {
            MemberAppealDetailExtension appeal = appealService.GetMemberAppealDetail(appealId, currentMember.getMemberId());
            if (appeal == null) {
                return MemberAuthFailed();
            }
            if (appeal.getStatus() == 3 || appeal.getStatus() == 4) {
                return new ResponseDto(400, Message.MEMBER_APPEAL_STATUS_ERROR);
            }
            //撤销
            progressService.AddProgress("您撤销了申诉", "您撤销了申诉", "dnt_appeal", appealId, currentMember.getMemberId(), 1);
            return appealService.UpdateAppealStatus(appealId, 4);
        } catch (Exception ex) {
            LOGGER.error("withdrawProgress", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
