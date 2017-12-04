package com.becheer.donation.controller.accepter;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAccepterService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* AccepterSecurityController
* Creator : xiaokepu
* Date : 2017-11-30
*/
@Controller
@RequestMapping("/accepter/security")
public class AccepterSecurityController extends BaseController{

    @Resource
    IAccepterService accepterService;

    @GetMapping(value = "/update")
    @Access(authorities = {Role.ACCEPTER})
    public String view(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return this.render("/accepter/update_pwd");
    }

    @ResponseBody
    @PostMapping("/validate")
    public ResponseDto getAccepterInfo(HttpServletRequest request,@RequestParam String acceptCode,@RequestParam String code) {
        try{
            Object objCode = request.getSession().getAttribute(ConstString.LOGIN_VERIFY_CODE);
        if (objCode == null) {
            return new ResponseDto(408, Message.LOGIN_CODE_NULL);
        }
        String sessionCode = objCode.toString().toUpperCase();
        if (!sessionCode.equals(code.toUpperCase())) {
            return new ResponseDto(403, Message.LOGIN_CODE_ERROE);
        }else{
            return new ResponseDto(200, "success");
        }
        }catch (Exception e){
            return new ResponseDto(403, Message.LOGIN_CODE_ERROE);
        }

    }

    @ResponseBody
    @PostMapping("/updatePw")
    public ResponseDto updatePw(HttpServletRequest request,@RequestParam String newPw,@RequestParam String acceptCode) {
        return accepterService.updatePw(newPw,acceptCode);
    }
}
