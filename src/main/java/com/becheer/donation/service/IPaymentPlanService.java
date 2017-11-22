package com.becheer.donation.service;

import com.becheer.donation.model.extension.payment.PaymentPlanExtension;

import java.util.List;

/*
* IPaymentPlanService
* Creator : xiaokepu
* Date : 2017-10-11
*/
public interface IPaymentPlanService {
    List<PaymentPlanExtension>GetPaymentPlan(Long contractId);

    /**
     * 根据contractId查金额
     */
    Integer GetAmountByContractId(Long contractId);

}
