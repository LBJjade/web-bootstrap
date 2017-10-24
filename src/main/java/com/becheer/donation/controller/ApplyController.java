package com.becheer.donation.controller;

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IIntentionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.becheer.donation.utils.HttpUtil.GetCurrentUser;

/*
* ApplyController
* Creator : xiaokepu
* Date : 2017-10-20
*/

@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController{
    @Resource
    IIntentionService intentionService;

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto AddApply(HttpServletRequest request, Intention intention){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        intention.setEnable(1);
        intention.setStatus(0);
        intention.setIntentionAmount(intention.getIntentionAmount()*100);
        return intentionService.AddIntention(intention);
    }
}
