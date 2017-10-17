package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
* IndexController
* Creator : xiaokepu
* Date : 2017-09-27
*/
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Access(authorities="member")
    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        return this.render("home/index");
    }
}
