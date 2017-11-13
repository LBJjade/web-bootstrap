package com.becheer.donation.controller;

/*
* LoginController 登录控制器
* Creator : xiaokepu
* Date : 2017-09-15
*/

import com.alibaba.fastjson.JSON;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.GenerateUtil;
import com.becheer.donation.utils.RegExUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Resource
    IMemberService memberService;

    @GetMapping("")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("login");
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto Submit(HttpServletRequest request, HttpServletResponse response, @RequestParam String code, @RequestParam String mobile, @RequestParam String pwd, @RequestParam boolean autoLogin) {
        if (code == null || code.length() == 0) {
            return new ResponseDto(400, Message.LOGIN_CODE_NULL);
        }
        if (!RegExUtil.checkMobile(mobile)) {
            return new ResponseDto(401, Message.LOGIN_MOBILE_ERROR);
        }
        if (pwd == null || pwd.trim().length() < 6) {
            return new ResponseDto(402, Message.LOGIN_PASSWORD_ERROR);
        }
        Object objCode = request.getSession().getAttribute(ConstString.LOGIN_VERIFY_CODE);
        if (objCode == null) {
            return new ResponseDto(408, Message.LOGIN_CODE_NULL);
        }
        String sessionCode = objCode.toString().toUpperCase();
        if (!sessionCode.equals(code.toUpperCase())) {
            return new ResponseDto(403, Message.LOGIN_CODE_ERROE);
        }
        ResponseDto<Member> result = memberService.Login(mobile, pwd);
        if (result.getCode() == 407) {
            Member member = result.getResult();
            MemberSessionExtension memberSessionExtension = new MemberSessionExtension();
            memberSessionExtension.setMemberId(member.getId());
            memberSessionExtension.setMemberName(member.getMemberName() == null ? "" : member.getMemberName());
            memberSessionExtension.setMobile(member.getMobile());
            memberSessionExtension.setRole(member.getRole());
            memberSessionExtension.setValidation(member.getValidation());
            memberSessionExtension.setAvator(member.getAvatorImg());
            request.getSession().setAttribute(ConstString.MEMBER_SESSION_CODE, JSON.toJSON(memberSessionExtension));
            //自动登录，设置cookie
            if (autoLogin) {
                Cookie cookie = new Cookie("member", memberSessionExtension.getMemberId() + "|" + GenerateUtil.genLoginCookie(memberSessionExtension.getMemberId()));
                cookie.setMaxAge(2592000); //设置cookie的过期时间是10s
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            memberService.updateLoginInfo(request.getRemoteAddr(), member.getId());
        }
        return result;
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseDto Logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(ConstString.MEMBER_SESSION_CODE);
        Cookie cookie = new Cookie("member", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseDto(200, Message.LOGINOUT_SUCCESS);
    }

    @PostMapping("/autoLogin")
    @ResponseBody
    public ResponseDto AutoLogin(HttpServletRequest request, @RequestParam long memberId, @RequestParam String cookie) {
        String vaildCookie = GenerateUtil.genLoginCookie(memberId);
        if (vaildCookie.equals(cookie)) {
            Member member = memberService.GetMember(memberId);
            if (member != null) {
                MemberSessionExtension memberSessionExtension = new MemberSessionExtension();
                memberSessionExtension.setMemberId(member.getId());
                memberSessionExtension.setMemberName(member.getMemberName() == null ? "" : member.getMemberName());
                memberSessionExtension.setMobile(member.getMobile());
                memberSessionExtension.setRole(member.getRole());
                memberSessionExtension.setValidation(member.getValidation());
                memberSessionExtension.setAvator(member.getAvatorImg());
                request.getSession().setAttribute(ConstString.MEMBER_SESSION_CODE, JSON.toJSON(memberSessionExtension));
                memberService.updateLoginInfo(request.getRemoteAddr(), member.getId());
                return new ResponseDto(200, Message.LOGIN_SUCCESS, memberSessionExtension);
            }
        }
        return new ResponseDto(500, Message.LOGIN_COOKIE_FAILED);
    }

}
