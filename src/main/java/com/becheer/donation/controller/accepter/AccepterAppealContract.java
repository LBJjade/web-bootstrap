package com.becheer.donation.controller.accepter;

/*
* 
* Creator : xiaokepu
* Date : 
*/

import com.becheer.donation.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/accepter/appeal")
public class AccepterAppealContract extends BaseController{

    @GetMapping("/list")
    public String appealListView(HttpServletRequest request){
        return render("/accepter/appeal");
    }

}
