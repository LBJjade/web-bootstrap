package com.becheer.donation.controller.accepter;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IAccepterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/*
* AccepterController
* Creator : xiaokepu
* Date : 2017-11-27
*/

@Controller
@RequestMapping("/accepter")
public class AccepterController extends BaseController {

    @Autowired
    IAccepterService accepterService;

    @GetMapping("")
    public String index(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return this.render("accepter/index");
    }

    @PostMapping("/info")
    public ResponseDto getAccepterInfo(HttpServletRequest request, @PathVariable long aid){
        return accepterService.getAccepterInfo(aid);
    }
}
