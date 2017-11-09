package com.becheer.donation.controller;


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
    @GetMapping(value = "")
    public String View(HttpServletRequest request)  {
        request.setAttribute("config", fileConfig);
        return this.render("change");
    }
}
