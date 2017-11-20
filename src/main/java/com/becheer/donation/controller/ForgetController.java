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
import javax.servlet.http.HttpServletRequest;

/*
* RegisterController 注册控制器
* Creator : xiaokepu
* Date : 2017-09-12
*/

@Controller
@RequestMapping("/forget")
public class ForgetController extends BaseController {

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

//    /**
//     * 账户验证
//     * @return
//     */
//    @PostMapping(value = "/validate")
//    @ResponseBody
//    public ResponseDto MenberValidate(HttpServletRequest request,  @RequestParam String mobile,@RequestParam String code) {
//        Member member = memberService.GetMemberByMobile(mobile);
//        if(member == null){
//            return MemberAuthFailed();
//        }else{
//            Object objCode=request.getSession().getAttribute(ConstString.LOGIN_VERIFY_CODE);
//            if (objCode==null){
//                return new ResponseDto(408, Message.LOGIN_CODE_NULL);
//            }
//            String sessionCode=objCode.toString().toUpperCase();
//            if (!sessionCode.equals(code.toUpperCase())){
//                return new ResponseDto(403,Message.LOGIN_CODE_ERROE);
//            }else{
//                return new ResponseDto(200,Message.LOGIN_CODE_SUCCESS);
//            }
//        }
//    }

    /**
     * 发送短信
     */
    @PostMapping(value = "/SendSms")
    @ResponseBody
    public ResponseDto SendSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam long tid) {
        if (!RegExUtil.checkMobile(mobile)) {
            return new ResponseDto(400, Message.VALIDATION_MOBILE_FAILED);
        }
        Member member = memberService.GetMemberByMobile(mobile);
        if (member == null) {
            return new ResponseDto(401, Message.REGISTER_NO_EXIST);
        }
        return smsService.SendSms(mobile, tid);
    }


    /**
     * 短信验证
     */
    @PostMapping(value = "/validate")
    @ResponseBody
    public ResponseDto CheckSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam String code, @RequestParam Long tid) {
//        ResponseDto result = smsService.CheckLoginCode(mobile,code);
//        if (result.getCode()==200){
//            MemberRegisterExtension memberRegisterExtension=new MemberRegisterExtension();
//            memberRegisterExtension.setMobile(mobile);
//            memberRegisterExtension.setRole(registerType);
//            request.getSession().setAttribute(ConstString.REGISTER_SMS_SESSION, memberRegisterExtension);
//        }
//        return result;
        //根据手机验证账号
        Member member = memberService.GetMemberByMobile(mobile);
        int enable = member.getEnable();
        if (enable == 0) {
            return new ResponseDto(402, Message.MEMBER_UNENABLE);
        } else {
            //验证短信
            ResponseDto result = smsService.CheckCode(mobile, code, tid);
            return result;
        }

    }


    /**
     * 修改密码
     */
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseDto ChangePw(HttpServletRequest request, @RequestParam String newPw, @RequestParam String mobile) {
        try {
            if (newPw == null || newPw.trim().length() < 6) {
                return new ResponseDto(403, "error pwd");
            }
            newPw = newPw.trim();
            int result = memberService.UpdatePw(newPw, mobile);
            return new ResponseDto(200, Message.PASSWORD_CHANG_SUCCESS, result);
        } catch (Exception e) {
            return new ResponseDto(402, Message.PASSWORD_CHANG_ERROR);
        }

    }
}
