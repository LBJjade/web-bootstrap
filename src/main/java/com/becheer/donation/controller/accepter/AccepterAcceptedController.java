package com.becheer.donation.controller.accepter;

/*
* AccepterAcceptedController
* Creator : xiaokepu
* Date : 2017-12-10
*/

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.allocate.AllocatePlanExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAllocatePlanService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/accepter/accepted")
public class AccepterAcceptedController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccepterAcceptedController.class);

    @Resource
    IAllocatePlanService allocatePlanService;

    @RequestMapping("")
    @Access(authorities = {Role.ACCEPTER})
    public String view(HttpServletRequest request){
        request.setAttribute("config", fileConfig);
        return render("/accepter/accepted");
    }

    @ResponseBody
    @RequestMapping("/list")
    public ResponseDto getAllocatePlan(HttpServletRequest request, @RequestParam int pageSize,@RequestParam int pageNum){
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        if (pageSize < 1 || pageSize > 50) {
            pageSize = 5;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        try{
            PageInfo<AllocatePlanExtension> result=allocatePlanService.getAllocatePlanByAccepterId(currentMember.getAccepterId(),pageNum,pageSize);
            return new ResponseDto(200,Message.ACCEPTER_ACCEPTED_GET_SUCCESS,result);
        }catch (Exception ex){
            LOGGER.error("getAllocatePlan", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
