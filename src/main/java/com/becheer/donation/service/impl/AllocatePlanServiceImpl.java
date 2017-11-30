package com.becheer.donation.service.impl;

import com.becheer.donation.dao.AllocatePlanMapper;
import com.becheer.donation.model.extension.allocate.AllocatePlanExtension;
import com.becheer.donation.service.IAllocatePlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* AllocatePlanServiceImpl
* Creator : xiaokepu
* Date : 2017-11-29
*/

@Service
public class AllocatePlanServiceImpl implements IAllocatePlanService {

    @Resource
    AllocatePlanMapper allocatePlanMapper;

    @Override
    public List<AllocatePlanExtension> getAllocatePlan(Long contractId) {
        return allocatePlanMapper.selectAllocatePlanByContractId(contractId);
    }

    @Override
    public PageInfo<AllocatePlanExtension> getAllocatePlanByAccepterId(Long accepterId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<AllocatePlanExtension> data=allocatePlanMapper.selectAllocatePlanByMemberId(accepterId);
        PageInfo<AllocatePlanExtension> pageInfo = new PageInfo<AllocatePlanExtension>(data);
        return pageInfo;
    }
}
