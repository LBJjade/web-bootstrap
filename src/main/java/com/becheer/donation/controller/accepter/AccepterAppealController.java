package com.becheer.donation.controller.accepter;

/*
* AccepterAppealController
* Creator : xiaokepu
* Date : 2017-12-04
*/

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.becheer.donation.model.extension.contract.AppropriationContractExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAppealService;
import com.becheer.donation.service.IAppropriationContractService;
import com.becheer.donation.service.IProgressService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import com.becheer.donation.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/accepter/appeal")
public class AccepterAppealController extends BaseController{

    @Resource
    IAppealService appealService;

    @Resource
    IProgressService progressService;

    @Resource
    IAppropriationContractService  appropriationContractService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccepterAppealController.class);

    @GetMapping("")
    public String appealListView(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return render("/accepter/appeal");
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto getAccepterAppealList(HttpServletRequest request,int pageSize,int pageNum){
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
            PageInfo<MemberAppealExtension> result = appealService.getAccepterAppeal(currentMember.getMemberId(), pageNum, pageSize);
            return new ResponseDto(200, Message.ACCEPTER_APPEAL_LIST_GET_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("getAccepterAppealList", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = {Role.ACCEPTER})
    @GetMapping("/add/{contractId}")
    public String ViewAppealEdit(HttpServletRequest request, @PathVariable long contractId) {
        request.setAttribute("config", fileConfig);
        try {
            AppropriationContractExtension contract= appropriationContractService.getAccepterContracttDetail(contractId);
            if (contract == null) {
                return render_404();
            } else if (contract.getAccepterId() != GetCurrentUser(request).getAccepterId()) {
                return render_404();
            } else {
                request.setAttribute("contract", contract);
                return this.render("/accepter/appeal_edit");
            }
        } catch (Exception ex) {
            LOGGER.error("ViewAppealEdit", ex.getMessage());
            return render_404();
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseDto add(HttpServletRequest request, @RequestParam String title, @RequestParam String method, @RequestParam String content, @RequestParam long contractId) {
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
            if (contractId == 0) {
                return new ResponseDto(400, Message.SUBMIT_APPEAL_ID_NULL);
            }
            long memberId = currentMember.memberId;
            ResponseDto result = appealService.InsertAppeal(title, method, content, contractId, 0,memberId);
            if (result.getCode()==200){
                progressService.AddProgress("您提交了申诉", "您提交了申诉", "dnt_appeal", (int)result.getResult(), memberId, 1);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("add", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = {Role.ACCEPTER})
    @GetMapping(value = "/{appealId}")
    public String getAppealDetail(HttpServletRequest request, @PathVariable long appealId) {
        request.setAttribute("config", fileConfig);
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            MemberAppealDetailExtension memberAppealDetailExtension = appealService.getAccepterAppealDetail(appealId, currentMember.getMemberId());
            if (memberAppealDetailExtension == null) {
                return render_404();
            } else {
                request.setAttribute("appeal", memberAppealDetailExtension);
                return render("home/appeal_detail");
            }
        } catch (Exception ex) {
            LOGGER.error("getAppealDetail", ex.getMessage());
            return render_404();
        }
    }


}
