package com.becheer.donation.dao;

/*
* DntPaymentPlanMapper
* Date :
*/

import com.becheer.donation.model.DntPaymentPlan;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface DntPaymentPlanMapper {
    int insert(DntPaymentPlan model);

    int update(DntPaymentPlan model);

    int updateReceived(String paylogRefTable, String orderNo, Date paymentDate, Integer receivedAmount);

    int updateDonate(String orderNo);
}
