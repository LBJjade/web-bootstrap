package com.becheer.donation.dao;

/*
* DntPaymentPlanMapper
* Date :
*/

import com.becheer.donation.model.DntPaymentPlan;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public interface DntPaymentPlanMapper {
    int insert(DntPaymentPlan model);

    int update(DntPaymentPlan model);

    int updateReceived(String paylogRefTable, String orderNo, Date paymentDate, Integer receivedAmount);

    Map updateDonate(String orderNo, Integer receivedAmount, Date paymentDate);

    Long selectIdByOrderNo(String no);

    DntPaymentPlan selectPaymentPlanByOrderNo(String no);
}
