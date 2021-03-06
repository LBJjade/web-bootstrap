package com.becheer.donation.controller;

/*
* SmsController 短信控制器
* Creator : xiaokepu
* Date : 2017-09-12
*/

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberRegisterExtension;
import com.becheer.donation.service.ISmsService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Sms")
public class SmsController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsController.class);

    @Resource
    private ISmsService smsService;

    /**
     * 发送短信
     */
    @PostMapping(value = "/SendSms")
    @ResponseBody
    public ResponseDto SendSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam long tid) {
        try {
            return smsService.SendSms(mobile, tid);
        }catch (Exception ex){
            LOGGER.error("SendSms", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    /**
     * 短信验证
     */
    @PostMapping(value = "/CheckSms")
    @ResponseBody
    public ResponseDto CheckSms(HttpServletRequest request,@RequestParam String mobile,@RequestParam String code,int registerType) {
        try {
            ResponseDto result = smsService.CheckLoginCode(mobile, code);
            if (result.getCode() == 200) {
                MemberRegisterExtension memberRegisterExtension = new MemberRegisterExtension();
                memberRegisterExtension.setMobile(mobile);
                memberRegisterExtension.setRole(registerType);
                request.getSession().setAttribute(ConstString.REGISTER_SMS_SESSION, memberRegisterExtension);
            }
            return result;
        }catch (Exception ex){
            LOGGER.error("CheckSms", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
