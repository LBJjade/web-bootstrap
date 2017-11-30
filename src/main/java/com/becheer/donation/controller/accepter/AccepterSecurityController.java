package com.becheer.donation.controller.accepter;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.strings.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
* AccepterSecurityController
* Creator : xiaokepu
* Date : 2017-11-30
*/
@Controller
@RequestMapping("/accepter/security")
public class AccepterSecurityController extends BaseController{

    @GetMapping(value = "/update")
    @Access(authorities = {Role.ACCEPTER})
    public String view(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return this.render("/accepter/update_pwd");
    }
}
