package com.becheer.donation.service;

/*
* IContractService
* Creator : xiaokepu
* Date : 2017-10-07
*/

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.MemberContractContentExtension;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.github.pagehelper.PageInfo;

public interface IContractService {
    /**
     *获取会员参与的项目
     */
    PageInfo<MemberContractExtension> GetContractList(long memberId, int pageNum, int pageSize);

    MemberContractDetailExtension GetMemberContractDetail(long contractId);

    MemberContractContentExtension GetContractContent(long contractId);

    ResponseDto UpdateContractStatuas(long contractId,long memberId);
}
