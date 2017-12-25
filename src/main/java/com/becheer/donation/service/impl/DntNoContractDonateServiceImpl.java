package com.becheer.donation.service.impl;

import com.becheer.donation.dao.DntNoContractDonateMapper;
import com.becheer.donation.model.DntNoContractDonate;
import com.becheer.donation.service.IDntNoContractDonateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DntNoContractDonateServiceImpl implements IDntNoContractDonateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DntNoContractDonateServiceImpl.class);

    @Resource
    private DntNoContractDonateMapper mapper;

    @Override
    public int insert(DntNoContractDonate dntNoContractDonate) {

        return mapper.insert(dntNoContractDonate);
    }

    @Override
    public int update(DntNoContractDonate dntNoContractDonate) {
        return mapper.update(dntNoContractDonate);
    }

    @Override
    public long selectProjectIdById(Integer id) {
        return mapper.selectProjectIdById(id);
    }

    @Override
    public Long selectMemberIdById(Integer id) {
        return mapper.selectMemberIdById(id);
    }
}
