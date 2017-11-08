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
@RequestMapping("/resetpw")
public class ResetPwController extends BaseController {

    @Resource
    private IMemberService memberService;

    /**
     *修改密码页面
     */
    @GetMapping(value = "")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("resetpw");
    }

    /**
     * 账户验证
     * @return
     */
    @PostMapping(value = "/validate")
    @ResponseBody
    public ResponseDto MenberValidate(HttpServletRequest request,  @RequestParam String mobile,@RequestParam String code) {
        Member member = memberService.GetMemberByMobile(mobile);
        if(member == null){
            return MemberAuthFailed();
        }else{
            Object objCode=request.getSession().getAttribute(ConstString.LOGIN_VERIFY_CODE);
            if (objCode==null){
                return new ResponseDto(408, Message.LOGIN_CODE_NULL);
            }
            String sessionCode=objCode.toString().toUpperCase();
            if (!sessionCode.equals(code.toUpperCase())){
                return new ResponseDto(403,Message.LOGIN_CODE_ERROE);
            }else{
                return new ResponseDto(200,Message.LOGIN_CODE_SUCCESS);
            }
        }
    }

    /**
     * 修改密码
     */
    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseDto ChangePw(HttpServletRequest request, @RequestParam String newPw, @RequestParam String mobile) {
        try{
            int result=memberService.UpdatePw(newPw,mobile);
            return new ResponseDto(200,Message.PASSWORD_CHANG_SUCCESS,result);
        }catch (Exception e){
            return new ResponseDto(402,Message.PASSWORD_CHANG_ERROR);
        }

    }
}
