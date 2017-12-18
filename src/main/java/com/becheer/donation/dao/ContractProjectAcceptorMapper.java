package com.becheer.donation.dao;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 包名: com.becheer.donation.dao
 * 文件说明: 描述当文件的用途
 * 创建人:LBJ
 * 创建日期: 2017/12/16 10:25
 * 版本:V1.0
 * Copyright  2017 广州品清科技有限公司
 */
@Component
public interface ContractProjectAcceptorMapper {
    Long selectIdByContractProjectId(Long id);

    List<Long> selectIdByContractProjectIds(List<Long> ids);
}
