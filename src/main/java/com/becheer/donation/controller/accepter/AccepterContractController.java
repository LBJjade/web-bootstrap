package com.becheer.donation.controller.accepter;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.AppropriationContractContentExtension;
import com.becheer.donation.model.extension.contract.AppropriationContractExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAppropriationContractService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* AccepterContractController
* Creator : xiaokepu
* Date : 2017-11-28
*/
@Controller
@RequestMapping("/accepter/contract")
public class AccepterContractController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccepterContractController.class);

    @Resource
    IAppropriationContractService appropriationContractService;

    @Access(authorities = {Role.ACCEPTER})
    @GetMapping("")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("/accepter/contract");
    }


    @PostMapping("/list")
    @ResponseBody
    public ResponseDto getContract(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum) {
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
            PageInfo<AppropriationContractExtension> result = appropriationContractService.getContractList(currentMember.getAccepterId(), pageNum, pageSize);
            return new ResponseDto(200, Message.ACCEPTER_GET_CONTRACT_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("GetContract", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @Access(authorities = {Role.ACCEPTER})
    @GetMapping(value = "/content/{contractId}")
    public String getContractContent(HttpServletRequest request, @PathVariable long contractId) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            AppropriationContractContentExtension result = appropriationContractService.selectAccepterContractContent(contractId);
            if (result == null) {
                return render_404();
            }
            if (result.getAccepterId() != currentMember.getAccepterId()) {
                return render_404();
            }
            request.setAttribute("contract", result);
            return render("/accepter/contract_content");
        } catch (Exception ex) {
            LOGGER.error("getContractContent", ex.getMessage());
            return render_404();
        }
    }
}
