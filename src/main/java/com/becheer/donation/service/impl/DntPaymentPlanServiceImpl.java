package com.becheer.donation.service.impl;

import com.becheer.donation.dao.DntPaymentPlanMapper;
import com.becheer.donation.model.DntPaymentPlan;
import com.becheer.donation.service.IDntPaymentPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class DntPaymentPlanServiceImpl implements IDntPaymentPlanService {

    private static final Logger logger = LoggerFactory.getLogger(DntPaymentPlanServiceImpl.class);

    @Resource
    private DntPaymentPlanMapper mapper;

    @Override
    public int insert(DntPaymentPlan model) {

        return mapper.insert(model);
    }

    @Override
    public int update(DntPaymentPlan model) {
        return mapper.update(model);
    }

    @Override
    public int updateReceived(String paylogRefTable, String orderNo, Date paymentDate, Integer receivedAmount) {
        return mapper.updateReceived(paylogRefTable, orderNo, paymentDate, receivedAmount);
    }

    @Override
    public Map updateDonate(String orderNo, Integer receivedAmount, Date paymentDate) {
        return mapper.updateDonate(orderNo, receivedAmount, paymentDate);
    }

    @Override
    public Map payment(String orderNo, Integer receivedAmount, Date paymentDate, String paylogRefTable, Integer paylogRefRecordId, Integer paymentMethod) {
        return mapper.payment(orderNo, receivedAmount, paymentDate, paylogRefTable, paylogRefRecordId, paymentMethod);
    }

    @Override
    public Long selectIdByOrderNo(String no) {
        return mapper.selectIdByOrderNo(no);
    }

    @Override
    public DntPaymentPlan selectPaymentPlanByOrderNo(String no) {
        return mapper.selectPaymentPlanByOrderNo(no);
    }
}
