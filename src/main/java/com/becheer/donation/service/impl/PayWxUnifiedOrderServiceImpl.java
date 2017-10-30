package com.becheer.donation.service.impl;

import com.becheer.donation.dao.PayWxUnifiedOrderMapper;
import com.becheer.donation.model.PayWxUnifiedOrder;
import com.becheer.donation.service.IPayWxUnifiedOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class PayWxUnifiedOrderServiceImpl implements IPayWxUnifiedOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayWxUnifiedOrderServiceImpl.class);

    @Resource
    private PayWxUnifiedOrderMapper mapper;

    @Override
    public int insert(Map<String, String> map) {

        return mapper.insert(map);
    }

    @Override
    public int insert(PayWxUnifiedOrder model) {

        return mapper.insert(model);
    }

    @Override
    public int update(Map<String, String> map) {
        return mapper.update(map);
    }

    @Override
    public int update(PayWxUnifiedOrder model) {
        return mapper.update(model);
    }

    @Override
    public PayWxUnifiedOrder getPayWxUnifiedOrderByOutTradeNo(String outTradeNo) {
        return mapper.getPayWxUnifiedOrderByOutTradeNo(outTradeNo);
    }

}
