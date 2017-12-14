package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeContractController.class);
    @Resource
    IMemberService memberService;

    @Access(authorities = {Role.PERSON, Role.COMPANY, Role.ACCEPTER})
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember.getRole() == 3) {
            return this.render("accepter/index");
        } else {
            return this.render("home/index");
        }
    }

    @Access(authorities = {Role.PERSON, Role.COMPANY})
    @GetMapping(value = "/edit")
    public String MemberEdit(HttpServletRequest request) {
        try {
            request.setAttribute("config", fileConfig);
            MemberSessionExtension currentMember = GetCurrentUser(request);
            MemberInfoExtension member = memberService.GetMemberExtensionById(currentMember.getMemberId());
            request.setAttribute("member", member);
            if (member.getRole() == 1) {
                return this.render("home/member_edit");
            } else {
                return this.render("home/company_edit");
            }
        } catch (Exception ex) {
            LOGGER.error("MemberEdit", ex.getMessage());
            return render_404();
        }
    }
}
