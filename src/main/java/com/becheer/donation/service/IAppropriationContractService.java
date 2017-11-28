package com.becheer.donation.service;

import com.becheer.donation.model.extension.contract.AppropriationContractExtension;
import com.github.pagehelper.PageInfo;

/*
* IAppropriationContractService
* Creator : xiaokepu
* Date : 2017-11-28
*/
public interface IAppropriationContractService {

    /**
     * 获取三方合同列表
     * @param memberId 会员ID
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return
     */
    PageInfo<AppropriationContractExtension> getContractList(long memberId, int pageNum, int pageSize);
}
