package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.accepter.AccepterInfoExtension;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAccepterService;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import com.becheer.donation.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* MemberController
* Creator : xiaokepu
* Date : 2017-09-27
*/
@Controller
@RequestMapping("/home/member")
public class MemberController extends BaseController {

    @Resource
    private IMemberService memberService;

    @Resource
    private IAccepterService accepterService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("/info")
    @ResponseBody
    public ResponseDto Submit(HttpServletRequest request, @RequestParam long memberId) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            return memberService.GetMemberById(memberId);
        } catch (Exception ex) {
            LOGGER.error("Submit", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto UpdateMember(HttpServletRequest request, MemberInfoExtension memberInfoExtension) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            memberInfoExtension.setValidation(2);
            memberInfoExtension.setId(currentMember.getMemberId());
            return memberService.UpdateMemberInfo(memberInfoExtension);
        } catch (Exception ex) {
            LOGGER.error("UpdateMember", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = {Role.PERSON, Role.COMPANY, Role.ACCEPTER})
    @GetMapping(value = "/avator")
    public String avatorUpload(HttpServletRequest request) {
        try {
            request.setAttribute("config", fileConfig);
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember.getRole() == 3) {
                AccepterInfoExtension accepter = accepterService.getAccepterByMemberId(currentMember.getMemberId());
                request.setAttribute("avator", accepter.getAvator());
            } else {
                MemberInfoExtension member = memberService.GetMemberExtensionById(currentMember.getMemberId());
                request.setAttribute("avator", member.getAvator());
            }
            return this.render("avator_upload");
        } catch (Exception ex) {
            LOGGER.error("avatorUpload", ex.getMessage());
            return render_404();
        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseDto uploadAvator(HttpServletRequest request, @RequestParam String imgStr) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            if (StringUtils.isEmpty(imgStr)) {
                return new ResponseDto(400, Message.MEMBER_AVATOR_NULL);
            }
            ResponseDto result = memberService.uploadAvator(currentMember.getMemberId(), imgStr, currentMember.getRole() == 3);
            if (result.getCode() == 200) {
                RedisUtil.delMemberKey(currentMember.getMemberId());
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("uploadAvator", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
