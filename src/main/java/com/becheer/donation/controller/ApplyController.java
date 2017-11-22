package com.becheer.donation.controller;

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IIntentionService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.becheer.donation.utils.HttpUtil.GetCurrentUser;

/*
* ApplyController
* Creator : xiaokepu
* Date : 2017-10-20
*/

@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Resource
    IIntentionService intentionService;

    @PostMapping("/submit")
    @ResponseBody
    public ResponseDto AddApply(HttpServletRequest request, Intention intention) {
        try{
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        intention.setEnable(1);
        intention.setStatus(0);
        intention.setIntentionAmount(intention.getIntentionAmount() * 100);
        return intentionService.AddIntention(intention);
        }catch(Exception ex){
            LOGGER.error("AddApply", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
