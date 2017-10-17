package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.payment.PaymentPlanExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.service.IContractService;
import com.becheer.donation.service.IPaymentPlanService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
* HomeContractController
* Creator : xiaokepu
* Date : 2017-09-30
*/
@Controller
@RequestMapping("/home/contract")
public class HomeContractController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeContractController.class);

    @Resource
    IContractService contractService;

    @Resource
    IPaymentPlanService paymentPlanService;

    @Access(authorities="member")
    @GetMapping("")
    public String View(HttpServletRequest request){
        return this.render("/home/contract");
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetContract(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        if (pageSize<1||pageSize>50){
            pageSize=5;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try {
            PageInfo<MemberContractExtension> result=contractService.GetContractList(currentMember.getMemberId(),pageNum,pageSize);
            return new ResponseDto(200, Message.MEMBER_GET_CONTRACT_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetContract", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities="member")
    @GetMapping(value = "/{contractId}")
    public String GetContractDetail(HttpServletRequest request,@PathVariable long contractId) {
        try{
            MemberContractDetailExtension memberContractDetailExtension=contractService.GetMemberContractDetail(contractId);
            if (memberContractDetailExtension==null){
                return render_404();
            }else{
                request.setAttribute("contract",memberContractDetailExtension);
                return render("home/contract_detail");
            }
        }catch(Exception ex){
            LOGGER.error("GetContract", ex);
            return render_500();
        }
    }

    @PostMapping("/payment")
    @ResponseBody
    public ResponseDto GetPaymentPlan(HttpServletRequest request, @RequestParam long contractId){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        try {
            List<PaymentPlanExtension> result=paymentPlanService.GetPaymentPlan(contractId);
            return new ResponseDto(200, Message.MEMBER_GET_PAYMENT_PLAN_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetContract", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
