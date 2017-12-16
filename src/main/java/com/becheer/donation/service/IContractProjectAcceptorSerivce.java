package com.becheer.donation.service;

import java.util.List;

/**
 * 包名: com.becheer.donation.service
 * 文件说明: 描述当文件的用途
 * 创建人:LBJ
 * 创建日期: 2017/12/16 10:27
 * 版本:V1.0
 * Copyright  2017 广州品清科技有限公司
 */
public interface IContractProjectAcceptorSerivce {
    Long selectByContractProjectId(Long id);

    List<Long> selectByContractProjectIds(List<Long> ids);
}
