package com.becheer.donation.service.impl;

import com.becheer.donation.dao.DntContractProjectMapper;
import com.becheer.donation.model.DntContractProject;
import com.becheer.donation.service.IDntContractProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 包名: com.becheer.donation.service.impl
 * 文件说明: 描述当文件的用途
 * 创建人:LBJ
 * 创建日期: 2017/12/12 17:14
 * 版本:V1.0
 * Copyright  2017 广州品清科技有限公司
 */
@Service
public class DntContractProjectImpl implements IDntContractProjectService{

    @Resource
    DntContractProjectMapper dntContractProjectMapper;

    @Override
    public List<DntContractProject> selectProjectIdBycontraId(Integer id) {
        return dntContractProjectMapper.selectProjectIdBycontraId(id);
    }
}
