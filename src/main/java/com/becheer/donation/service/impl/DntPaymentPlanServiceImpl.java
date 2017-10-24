package com.becheer.donation.service.impl;

import com.becheer.donation.dao.DntPaymentPlanMapper;
import com.becheer.donation.model.DntPaymentPlan;
import com.becheer.donation.service.IDntPaymentPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
