package com.becheer.donation.controller;

/*
* LoginController 登录控制器
* Creator : xiaokepu
* Date : 2017-09-15
*/

import com.alibaba.fastjson.JSON;
import com.becheer.donation.model.Accepter;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAccepterService;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.GenerateUtil;
import com.becheer.donation.utils.RegExUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.becheer.donation.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Resource
    IMemberService memberService;

    @Resource
    IAccepterService accepterService;

    @GetMapping("")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("login");
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto Submit(HttpServletRequest request, HttpServletResponse response, @RequestParam String code, @RequestParam String mobile, @RequestParam String pwd, @RequestParam boolean autoLogin) {
        try {
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
                memberSessionExtension.setAccepterId(member.getAccepterId());

                request.getSession().setAttribute(ConstString.LOGIN_SESSION_NAME, JSON.toJSON(memberSessionExtension));
                //自动登录，设置cookie
                if (autoLogin) {
                    Cookie cookie = new Cookie(ConstString.LOGIN_COOKIE_NAME, memberSessionExtension.getMemberId() + "|" + GenerateUtil.genLoginCookie(memberSessionExtension.getMemberId()));
                    cookie.setMaxAge(2592000);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                memberService.updateLoginInfo(request.getRemoteAddr(), member.getId());
            }
            return result;
        }catch (Exception ex) {
            LOGGER.error("Submit", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/acLogin")
    @ResponseBody
    public ResponseDto accepterLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam String code, @RequestParam String authCode, @RequestParam String pwd, @RequestParam boolean autoLogin) {
        try {
            if (code == null || code.length() == 0) {
                return new ResponseDto(400, Message.LOGIN_CODE_NULL);
            }
            if (StringUtil.isNull(authCode)) {
                return new ResponseDto(401, Message.LOGIN_AUTH_NO_NULL);
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
            ResponseDto<Accepter> result = accepterService.login(authCode, pwd);
            if (result.getCode() == 200) {
                Accepter accepter = result.getResult();
                MemberSessionExtension memberSessionExtension = new MemberSessionExtension();
                memberSessionExtension.setMemberId(accepter.getMemberId());
                memberSessionExtension.setMemberName(accepter.getName());
                memberSessionExtension.setMobile(accepter.getMobile());
                memberSessionExtension.setRole(3);
                memberSessionExtension.setValidation(3);
                memberSessionExtension.setAvator(accepter.getAvator());
                memberSessionExtension.setAccepterId(accepter.getId());
                //写受捐人Session
                request.getSession().setAttribute(ConstString.LOGIN_SESSION_NAME, JSON.toJSON(memberSessionExtension));
                //用户勾选了自动登录，登录
                if (autoLogin) {
                    Cookie cookie = new Cookie(ConstString.LOGIN_COOKIE_NAME, memberSessionExtension.getMemberId() + "|" + GenerateUtil.genLoginCookie(memberSessionExtension.getMemberId()));
                    cookie.setMaxAge(2592000);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            return result;
        }catch (Exception ex) {
            LOGGER.error("accepterLogin", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }



    @PostMapping("/logout")
    @ResponseBody
    public ResponseDto Logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute(ConstString.LOGIN_SESSION_NAME);
            Cookie cookie = new Cookie(ConstString.LOGIN_COOKIE_NAME, null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new ResponseDto(200, Message.LOGINOUT_SUCCESS);
        }catch (Exception ex) {
            LOGGER.error("Logout", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/autoLogin")
    @ResponseBody
    public ResponseDto AutoLogin(HttpServletRequest request, @RequestParam long memberId, @RequestParam String cookie) {
        try {
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
                    memberSessionExtension.setAccepterId(member.getAccepterId());
                    request.getSession().setAttribute(ConstString.LOGIN_SESSION_NAME, JSON.toJSON(memberSessionExtension));
                    memberService.updateLoginInfo(request.getRemoteAddr(), member.getId());
                    return new ResponseDto(200, Message.LOGIN_SUCCESS, memberSessionExtension);
                }
            }
            return new ResponseDto(500, Message.LOGIN_COOKIE_FAILED);
        }catch (Exception ex) {
            LOGGER.error("AutoLogin", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

}
