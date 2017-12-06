package com.becheer.donation.controller;

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.report.IndexReport;
import com.becheer.donation.service.*;
import com.becheer.donation.strings.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 */
@Controller
@RequestMapping({"/index", ""})
public class IndexController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private IProjectService projectService;

    @Resource
    IReportDonateService reportDonateService;

    @Resource
    IProgressService progressService;

    @Resource
    IIntentionExtensionService intentionExtensionService;

    @Resource
    IIntentionService intentionService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("index");
    }

    /**
     * 获取首页项目列表
     */
    @PostMapping(value = "/project")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request, @RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            if (pageNum <= 0) {
                pageNum = 1;
            }
            if (pageSize > 50 || pageSize <= 0) {
                pageSize = 10;
            }
            return ResponseDto.GetResponse(200, "success", projectService.GetProjectList(pageNum, pageSize));
        } catch (Exception ex) {
            LOGGER.error("GetProject", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    /**
     * 获取捐赠统计
     */
    @PostMapping(value = "/report")
    @ResponseBody
    public ResponseDto GetReport(HttpServletRequest request) {
        try {
            IndexReport result = reportDonateService.GetIndexReport();
            return ResponseDto.GetResponse(200, "success", result);
        } catch (Exception ex) {
            LOGGER.error("GetReport", ex);
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }

    /**
     * 添加捐赠意向
     */
    @PostMapping(value = "/addIntention")
    @ResponseBody
    public ResponseDto AddIntention(HttpServletRequest request, @RequestParam Long projectId, @RequestParam Long projectTypeId, @RequestParam long intentionAmount, @RequestParam String contactPhone, @RequestParam String remark) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            int MemberValidation = currentMember.getValidation();
            if(MemberValidation != 3){
                return MemberAuthFailed();
            }
            if (currentMember == null) {
                return MemberAuthFailed();
            } else {
                //获取memberId
                Long memberId = currentMember.getMemberId();
                Intention intention = new Intention();
                //生成捐赠意向流水号
                String no = intentionService.generateContractNo();
                //写入itention表
                intention.setIntentionNo(no);
                intention.setProjectId(projectId);
                intention.setProjectTypeId(projectTypeId);
                intention.setIntentionAmount(intentionAmount);
                intention.setContactPhone(contactPhone);
                intention.setRemark(remark);
                intention.setEnable(1);
                intention.setStatus(0);
                intention.setMemberId(currentMember.getMemberId());
                ResponseDto result = intentionExtensionService.AddIntentionExtension(intention);
                if (result.getCode() == 200) {
                    progressService.AddProgress("您提交了捐赠意向", "您提交了捐赠意向", "dnt_intention", (long) result.getResult(), currentMember.getMemberId(), 1);
                }
                return result;
            }

        } catch (Exception ex) {
            LOGGER.error("AddIntention", ex);
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }

}
