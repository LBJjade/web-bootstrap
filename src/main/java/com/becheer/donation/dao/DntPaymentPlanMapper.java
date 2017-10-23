package com.becheer.donation.dao;

/*
* DntPaymentPlanMapper
* Date :
*/

import com.becheer.donation.model.DntPaymentPlan;
import org.springframework.stereotype.Component;

@Component
public interface DntPaymentPlanMapper {
    int insert(DntPaymentPlan model);

    int update(DntPaymentPlan model);
}
