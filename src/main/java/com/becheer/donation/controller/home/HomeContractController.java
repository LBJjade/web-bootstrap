package com.becheer.donation.controller.home;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.MemberContractContentExtension;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.payment.PaymentPlanExtension;
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

    @Access(authorities = "member")
    @GetMapping("")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("/home/contract");
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetContract(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum) {
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
        try {
            PageInfo<MemberContractExtension> result = contractService.GetContractList(currentMember.getMemberId(), pageNum, pageSize);
            return new ResponseDto(200, Message.MEMBER_GET_CONTRACT_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("GetContract", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = "member")
    @GetMapping(value = "/{contractId}")
    public String GetContractDetail(HttpServletRequest request, @PathVariable long contractId) {
        request.setAttribute("config", fileConfig);
        try {
            MemberContractDetailExtension memberContractDetailExtension = contractService.GetMemberContractDetail(contractId);
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (memberContractDetailExtension.getMemberId() != currentMember.getMemberId()) {
                return render_404();
            }
            if (memberContractDetailExtension == null) {
                return render_404();
            } else {
                request.setAttribute("contract", memberContractDetailExtension);
                return render("home/contract_detail");
            }
        } catch (Exception ex) {
            LOGGER.error("GetContract", ex);
            return render_500();
        }
    }

    @PostMapping("/payment")
    @ResponseBody
    public ResponseDto GetPaymentPlan(HttpServletRequest request, @RequestParam long contractId) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        try {
            MemberContractContentExtension contract = contractService.GetContractContent(contractId);
            if (contract.getMemberId() != currentMember.getMemberId()) {
                return MemberAuthFailed();
            }

            List<PaymentPlanExtension> result = paymentPlanService.GetPaymentPlan(contractId);
            return new ResponseDto(200, Message.MEMBER_GET_PAYMENT_PLAN_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("GetContract", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = "member")
    @GetMapping(value = "/content/{contractId}")
    public String GetContractContent(HttpServletRequest request, @PathVariable long contractId) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            MemberContractContentExtension result = contractService.GetContractContent(contractId);
            if (result.getMemberId() != currentMember.getMemberId()) {
                return render_404();
            }
            if (result == null) {
                return render_404();
            } else {
                request.setAttribute("contract", result);
                return render("home/contract_content");
            }
        } catch (Exception ex) {
            LOGGER.error("GetContractContent", ex);
            return render_500();
        }
    }

    @PostMapping("/sign")
    @ResponseBody
    public ResponseDto SignContract(HttpServletRequest request, @RequestParam long contractId) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        MemberContractContentExtension contract = contractService.GetContractContent(contractId);
        if (contract.getMemberId() != currentMember.getMemberId()) {
            return MemberAuthFailed();
        }
        try {
            return contractService.UpdateContractStatuas(contractId, currentMember.getMemberId());
        } catch (Exception ex) {
            LOGGER.error("SignContract", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

}
