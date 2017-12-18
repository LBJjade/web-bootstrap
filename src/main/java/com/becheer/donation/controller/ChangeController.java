package com.becheer.donation.controller;


import com.becheer.donation.interfaces.Access;
import com.becheer.donation.strings.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/change")
public class ChangeController extends BaseController{
    /**
     *修改密码页面
     */
    @Access(authorities = {Role.PERSON,Role.COMPANY})
    @GetMapping(value = "")
    public String View(HttpServletRequest request)  {
        request.setAttribute("config", fileConfig);
        return this.render("change");
    }
}
