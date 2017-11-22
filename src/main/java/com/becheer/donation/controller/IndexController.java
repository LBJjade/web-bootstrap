package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.intention.IntentionDonateExtension;
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
    IIntentionExtensionService intentionExtensionService;

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
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize > 50 || pageSize <= 0) {
            pageSize = 10;
        }
        return ResponseDto.GetResponse(200, "success", projectService.GetProjectList(pageNum, pageSize));
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
    public ResponseDto AddIntention(HttpServletRequest request, @RequestParam Long projectId, @RequestParam Long projectTypeId, @RequestParam long intentionAmount, @RequestParam String contactPhone, @RequestParam String remark,@RequestParam int enable, @RequestParam int status) {
        try {
            MemberSessionExtension currentMember=GetCurrentUser(request);
            if (currentMember==null){
                return MemberAuthFailed();
            }
            IntentionDonateExtension intentionDonateExtension=new IntentionDonateExtension();
            intentionDonateExtension.setProjectId(projectId);
            intentionDonateExtension.setProjectTypeId(projectTypeId);
            intentionDonateExtension.setIntentionAmount(intentionAmount);
            intentionDonateExtension.setContactPhone(contactPhone);
            intentionDonateExtension.setRemark(remark);
            intentionDonateExtension.setEnable(enable);
            intentionDonateExtension.setStatus(status);
            intentionDonateExtension.setMemberId(currentMember.getMemberId());
//            return intentionService.AddIntention(intentionDonateExtension);
            return intentionExtensionService.AddIntentionExtension(intentionDonateExtension);
        } catch (Exception ex) {
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }

}
