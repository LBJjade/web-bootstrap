package com.becheer.donation.service.impl;

/*
* PaymentPlanServiceImpl
* Creator : xiaokepu
* Date : 2017-10-11
*/

import com.becheer.donation.dao.PaymentPlanMapper;
import com.becheer.donation.model.extension.payment.PaymentPlanExtension;
import com.becheer.donation.service.IPaymentPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentPlanServiceImpl implements IPaymentPlanService {

    @Resource
    PaymentPlanMapper paymentPlanMapper;

    @Override
    public List<PaymentPlanExtension> GetPaymentPlan(Long contractId) {
        return paymentPlanMapper.SelectPaymentPlanByContractId(contractId);
    }
}
