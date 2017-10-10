package com.becheer.donation.service.impl;

/*
* ContractServiceImpl
* Creator : xiaokepu
* Date : 2017-10-08
*/

import com.becheer.donation.dao.ContractMapper;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.becheer.donation.service.IContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContractServiceImpl implements IContractService {

    @Resource
    ContractMapper contractMapper;

    @Override
    public PageInfo<MemberContractExtension> GetContractList(long memberId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MemberContractExtension> data=contractMapper.SelectContractByMemberId(memberId);
        PageInfo<MemberContractExtension> pageInfo=new PageInfo<>(data);
        return pageInfo;
    }

    @Override
    public MemberContractDetailExtension GetMemberContractDetail(long contractId) {
        return contractMapper.SelectContractDetail(contractId);
    }
}
