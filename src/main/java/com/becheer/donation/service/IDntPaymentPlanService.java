package com.becheer.donation.service;

/*
* IDntPaymentPlanService 支付计划服务接口
* Date :
*/

import com.becheer.donation.model.DntPaymentPlan;

import java.util.Date;
import java.util.Map;

public interface IDntPaymentPlanService {

    int insert(DntPaymentPlan model);

    int update(DntPaymentPlan model);

    int updateReceived(String paylogRefTable, String orderNo, Date paymentDate, Integer receivedAmount);

    Map updateDonate(String orderNoDate ,Date paymentDate, Integer receivedAmount);

}
