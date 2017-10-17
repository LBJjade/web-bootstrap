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
import com.becheer.donation.utils.RegExUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Resource
    IMemberService memberService;

    @GetMapping("")
    public String View(HttpServletRequest request){
        return this.render("login");
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto Submit(HttpServletRequest request, @RequestParam String code,@RequestParam String mobile,@RequestParam String pwd){
        if (code==null||code.length()==0){
            return new ResponseDto(400,Message.LOGIN_CODE_NULL);
        }
        if (!RegExUtil.checkMobile(mobile)){
            return new ResponseDto(401,Message.LOGIN_MOBILE_ERROR);
        }
        if (pwd==null||pwd.trim().length()<8){
            return new ResponseDto(402,Message.LOGIN_PASSWORD_ERROR);
        }
        Object objCode=request.getSession().getAttribute(ConstString.LOGIN_VERIFY_CODE);
        if (objCode==null){
            return new ResponseDto(408, Message.LOGIN_CODE_NULL);
        }
        String sessionCode=objCode.toString().toUpperCase();
        if (!sessionCode.equals(code.toUpperCase())){
            return new ResponseDto(403,Message.LOGIN_CODE_ERROE);
        }
        ResponseDto<Member> result = memberService.Login(mobile,pwd);
        if (result.getCode()==407){
            MemberSessionExtension memberSessionExtension=new MemberSessionExtension();
            memberSessionExtension.setMemberId(result.getResult().getId());
            memberSessionExtension.setMemberName(result.getResult().getMemberName()==null?"":result.getResult().getMemberName());
            memberSessionExtension.setMobile(result.getResult().getMobile());
            memberSessionExtension.setRole(result.getResult().getRole());
            memberSessionExtension.setValidation(result.getResult().getValidation());

            request.getSession().setAttribute(ConstString.MEMBER_SESSION_CODE, JSON.toJSON(memberSessionExtension));
        }
        return result;
    }

}
