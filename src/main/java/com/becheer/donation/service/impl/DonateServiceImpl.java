package com.becheer.donation.service.impl;

import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;
import com.becheer.donation.service.IDonateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DonateServiceImpl implements IDonateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonateServiceImpl.class);

    public WxPayPrepayExtension donate(Donate donate) {
        Integer projectTypeId = donate.getProjectTypeId();
        Integer projectId = donate.getProjectId();
        long memberId = donate.getMemberId();
        Integer amount = donate.getAmount();

        // TODO 1.写入直接捐赠表 dnt_no_contract_donate


        // TODO 2.写入付款计划表 dnt_payment_plan


        return null;
    }
}

