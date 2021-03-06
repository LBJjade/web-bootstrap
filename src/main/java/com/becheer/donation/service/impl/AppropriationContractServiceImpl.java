package com.becheer.donation.service.impl;

import com.becheer.donation.dao.AppropriationContractMapper;
import com.becheer.donation.model.extension.contract.AppropriationContractContentExtension;
import com.becheer.donation.model.extension.contract.AppropriationContractExtension;
import com.becheer.donation.service.IAppropriationContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* 
* Creator : xiaokepu
* Date : 
*/
@Service
public class AppropriationContractServiceImpl implements IAppropriationContractService {

    @Resource
    AppropriationContractMapper appropriationContractMapper;

    @Override
    public PageInfo<AppropriationContractExtension> getContractList(long accepterId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AppropriationContractExtension> data = appropriationContractMapper.selectAccepterContractList(accepterId);
        PageInfo<AppropriationContractExtension> pageInfo = new PageInfo<>(data);
        return pageInfo;
    }

    @Override
    public AppropriationContractContentExtension getAccepterContractContent(long contractId) {
        return appropriationContractMapper.selectAccepterContractContent(contractId);
    }

    @Override
    public AppropriationContractExtension getAccepterContracttDetail(long contractId) {
        return appropriationContractMapper.selectAccepterContractDetail(contractId);
    }
}
