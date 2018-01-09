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

    Map updateDonate(String orderNoDate, Integer receivedAmount, Date paymentDate);

    Map payment(String orderNo, Integer receivedAmount, Date paymentDate, String paylogRefTable, Integer paylogRefRecordId, Integer paymentMethod);

    Long selectIdByOrderNo(String no);

    DntPaymentPlan selectPaymentPlanByOrderNo(String no);

}
