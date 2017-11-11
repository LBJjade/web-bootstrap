package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.OssUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @PostMapping("/info")
    @ResponseBody
    public ResponseDto Submit(HttpServletRequest request, @RequestParam long memberId){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }

        return memberService.GetMemberById(memberId);
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto UpdateMember(HttpServletRequest request, MemberInfoExtension memberInfoExtension){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        memberInfoExtension.setValidation(2);
        memberInfoExtension.setId(currentMember.getMemberId());
        return memberService.UpdateMemberInfo(memberInfoExtension);
    }

    @Access(authorities="member")
    @GetMapping(value = "/avator")
    public String avatorUpload(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        request.setAttribute("member",GetCurrentUser(request));
        return this.render("home/avator_upload");
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseDto uploadAvator(HttpServletRequest request,@RequestParam String imgStr) {
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        if (StringUtils.isEmpty(imgStr)){
            return new ResponseDto(400,Message.MEMBER_AVATOR_NULL);
        }
        return memberService.uploadAvator(currentMember.getMemberId(),imgStr);
    }
}
