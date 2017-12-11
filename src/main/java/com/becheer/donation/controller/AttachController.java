package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.attach.AttachExtension;
import com.becheer.donation.model.extension.contract.AppropriationContractExtension;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAppropriationContractService;
import com.becheer.donation.service.IAttachService;
import com.becheer.donation.service.IContractService;
import com.becheer.donation.strings.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 包名: com.becheer.donation.controller
 * 文件说明: AttachController
 * 创建人:amber
 * 创建日期: 2017/12/7 18:59
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */

@Controller
@RequestMapping("/attach")
public class AttachController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttachController.class);

    @Resource
    IAttachService attachService;

    @Resource
    IContractService contractService;

    @Resource
    IAppropriationContractService appropriationContractService;

    @ResponseBody
    @PostMapping("/contract")
    public ResponseDto getContractAttach(HttpServletRequest request, @RequestParam long contractId){
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            MemberContractDetailExtension contract = contractService.GetMemberContractDetail(contractId);
            if (contract == null || contract.getMemberId() != currentMember.getMemberId()) {
                return MemberAuthFailed();
            }
            List<AttachExtension> attachList = attachService.getAttach("dnt_contract", contractId);
            return new ResponseDto(200,Message.MEMBER_CONTRACT_ATTACH_GET_SUCCESS,attachList);
        }catch (Exception ex){
            LOGGER.error("getContractAttach", ex.getMessage());
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/appropriation")
    public ResponseDto getAppropriationAttach(HttpServletRequest request, @RequestParam long contractId){
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            AppropriationContractExtension contract = appropriationContractService.getAccepterContracttDetail(contractId);
            if (contract == null || contract.getAccepterId() != currentMember.getAccepterId()) {
                return MemberAuthFailed();
            }
            List<AttachExtension> attachList = attachService.getAttach("dnt_appropriation_contract", contractId);
            return new ResponseDto(200,Message.MEMBER_CONTRACT_ATTACH_GET_SUCCESS,attachList);
        }catch (Exception ex){
            LOGGER.error("getAppropriationAttach", ex.getMessage());
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }
}
