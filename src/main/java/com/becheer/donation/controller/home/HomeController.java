package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* IndexController
* Creator : xiaokepu
* Date : 2017-09-27
*/
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Resource
    IMemberService memberService;

    @Access(authorities="member")
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("home/index");
    }

    @Access(authorities="member")
    @GetMapping(value = "/edit")
    public String MemberEdit(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        MemberSessionExtension currentMember=GetCurrentUser(request);
        MemberInfoExtension member=memberService.GetMemberExtensionById(currentMember.getMemberId());
        request.setAttribute("member",member);
        return this.render("home/member_edit");
    }
}
