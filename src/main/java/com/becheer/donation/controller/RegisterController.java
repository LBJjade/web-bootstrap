package com.becheer.donation.controller;

import com.alibaba.fastjson.JSON;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberRegisterExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.service.ISmsService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.RegExUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;

/*
* RegisterController 注册控制器
* Creator : xiaokepu
* Date : 2017-09-12
*/

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Resource
    private IMemberService memberService;

    @Resource
    private ISmsService smsService;

    /**
     *注册页面
     */
    @GetMapping(value = "")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("register");
    }

    /**
     * 提交注册
     * @return
     */
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseDto SendSms(HttpServletRequest request,  @RequestParam String pwd) {
        MemberRegisterExtension memberRegisterExtension = (MemberRegisterExtension) request.getSession().getAttribute(ConstString.REGISTER_SMS_SESSION);
        if (memberRegisterExtension == null) {
            return new ResponseDto(401, "Bad Request");
        }
        if (pwd == null || pwd.trim().length() < 8) {
            return new ResponseDto(402, "error pwd");
        }
        pwd = pwd.trim();
        ResponseDto result = memberService.SubmitRegister(memberRegisterExtension.getMobile(), pwd, memberRegisterExtension.getRole());
        if (result.getCode() == 200) {
            //注册成功，删除Session
            request.getSession().removeAttribute(ConstString.REGISTER_SMS_SESSION);
            //用户Session
            MemberSessionExtension memberSessionExtension = new MemberSessionExtension();
            Member member = (Member) result.getResult();
            memberSessionExtension.setMemberId(member.getId());
            memberSessionExtension.setMemberName(member.getMemberName() == null ? "" : member.getMemberName());
            memberSessionExtension.setMobile(member.getMobile());
            memberSessionExtension.setRole(member.getRole());
            memberSessionExtension.setValidation(member.getValidation());
            memberSessionExtension.setAvator("image/avator/default.jpg");
            request.getSession().setAttribute(ConstString.MEMBER_SESSION_CODE, JSON.toJSON(memberSessionExtension));
        }
        return result;
    }

    /**
     * 发送短信
     */
    @PostMapping(value = "/SendSms")
    @ResponseBody
    public ResponseDto SendRegisterSms(HttpServletRequest request, @RequestParam String mobile) {
        if (!RegExUtil.checkMobile(mobile)){
            return new ResponseDto(400,Message.VALIDATION_MOBILE_FAILED);
        }
        Member member=memberService.GetMemberByMobile(mobile);
        if (member!=null){
            return new ResponseDto(401,Message.REGISTER_MOBILE_EXIST);
        }
        return smsService.SendSms(mobile,1);
    }
}
