package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* MemberController
* Creator : xiaokepu
* Date : 2017-09-27
*/
@Controller
@RequestMapping("/home/member")
public class MemberController extends BaseController {

    @Resource
    private IMemberService memberService;

    @PostMapping("/info")
    @ResponseBody
    public ResponseDto Submit(HttpServletRequest request, @RequestParam long memberId){
        return memberService.GetMemberById(memberId);
    }
}
