package com.becheer.donation.dao;

import com.becheer.donation.model.extension.payment.PaymentPlanExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* PaymentPlanMapper
* Creator : xiaokepu
* Date : 2017-10-11
*/
@Component
public interface PaymentPlanMapper {
    List<PaymentPlanExtension> SelectPaymentPlanByContractId(Long contractId);
}
