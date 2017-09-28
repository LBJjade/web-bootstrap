package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberRegisterExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.ConstString;
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
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Resource
    private IMemberService memberService;

    /**
     *注册页面
     */
    @GetMapping(value = "")
    public String View(HttpServletRequest request) {
        return this.render("register");
    }

    /**
     * 提交注册
     * @return
     */
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseDto SendSms(HttpServletRequest request,  @RequestParam String pwd) {
        MemberRegisterExtension memberRegisterExtension =(MemberRegisterExtension)request.getSession().getAttribute(ConstString.REGISTER_SMS_SESSION);
        if (memberRegisterExtension==null){
            return new ResponseDto(401,"Bad Request");
        }
        if (pwd==null||pwd.trim().length()<8){
            return new ResponseDto(402,"error pwd");
        }
        pwd=pwd.trim();
        ResponseDto result = memberService.SubmitRegister(memberRegisterExtension.getMobile(),pwd,memberRegisterExtension.getRole());
        if (result.getCode()==200){
            //注册成功，删除Session
            request.getSession().removeAttribute(ConstString.REGISTER_SMS_SESSION);
        }
        return result;
    }
}
