package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ContractProjectAcceptorMapper;
import com.becheer.donation.service.IContractProjectAcceptorSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 包名: com.becheer.donation.service.impl
 * 文件说明: 描述当文件的用途
 * 创建人:LBJ
 * 创建日期: 2017/12/16 10:28
 * 版本:V1.0
 * Copyright  2017 广州品清科技有限公司
 */
@Service
public class ContractProjectAcceptorServiceImpl implements IContractProjectAcceptorSerivce {
    @Resource
    private ContractProjectAcceptorMapper contractProjectAcceptorMapper;

    @Override
    public Long selectByContractProjectId(Long id) {
        return contractProjectAcceptorMapper.selectIdByContractProjectId(id);
    }

    @Override
    public List<Long> selectByContractProjectIds(List<Long> ids) {
        return contractProjectAcceptorMapper.selectIdByContractProjectIds(ids);
    }
}
