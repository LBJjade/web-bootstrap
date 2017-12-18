package com.becheer.donation.controller;

import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.service.ISmsService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.RegExUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* RegisterController 注册控制器
* Creator : xiaokepu
* Date : 2017-09-12
*/

@Controller
@RequestMapping("/forget")
public class ForgetController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgetController.class);

    @Resource
    private IMemberService memberService;

    @Resource
    private ISmsService smsService;

    /**
     * 修改密码页面
     */
    @GetMapping(value = "")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("forget");
    }

    /**
     * 发送短信
     */
    @PostMapping(value = "/SendSms")
    @ResponseBody
    public ResponseDto SendSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam long tid) {
        try {
            if (!RegExUtil.checkMobile(mobile)) {
                return new ResponseDto(400, Message.VALIDATION_MOBILE_FAILED);
            }
            Member member = memberService.GetMemberByMobile(mobile);
            if (member == null) {
                return new ResponseDto(401, Message.REGISTER_NO_EXIST);
            }
            return smsService.SendSms(mobile, tid);
        } catch (Exception ex) {
            LOGGER.error("SendSms", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


    /**
     * 短信验证
     */
    @PostMapping(value = "/validate")
    @ResponseBody
    public ResponseDto CheckSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam String code, @RequestParam Long tid) {
        try {
            Member member = memberService.GetMemberByMobile(mobile);
            int enable = member.getEnable();
            if (enable == 0) {
                return new ResponseDto(402, Message.MEMBER_UNENABLE);
            } else {
                //验证短信
                ResponseDto result = smsService.CheckCode(mobile, code, tid);
                return result;
            }
        } catch (Exception ex) {
            LOGGER.error("CheckSms", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


    /**
     * 修改密码
     */
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseDto ChangePw(HttpServletRequest request, HttpServletResponse response, @RequestParam String newPw, @RequestParam String mobile) {
        try {
            if (newPw == null || newPw.trim().length() < 8) {
                return new ResponseDto(403, "error pwd");
            }
            newPw = newPw.trim();
            int result = memberService.UpdatePw(newPw, mobile);
            if (result > 0) {
                request.getSession().removeAttribute(ConstString.LOGIN_SESSION_NAME);
                Cookie cookie = new Cookie(ConstString.LOGIN_COOKIE_NAME, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                return new ResponseDto(200, Message.PASSWORD_CHANG_SUCCESS);
            } else {
                return new ResponseDto(400, Message.PASSWORD_CHANG_ERROR);
            }
        } catch (Exception ex) {
            LOGGER.error("ChangePw", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }

    }
}
