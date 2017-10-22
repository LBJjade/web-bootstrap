package com.becheer.donation.service;

/*
* IDntPaymentPlanService 支付计划服务接口
* Date :
*/

import com.becheer.donation.model.DntPaymentPlan;

public interface IDntPaymentPlanService {

    public int insert(DntPaymentPlan model);

    public int update(DntPaymentPlan model);
}
