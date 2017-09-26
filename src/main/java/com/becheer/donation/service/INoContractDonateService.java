package com.becheer.donation.service;

import com.becheer.donation.model.extension.contract.NoContractDonateExtension;

import java.util.List;

/*
* INoContractDonateService:无合同捐赠Service接口
* Creator : xiaokepu
* Date : 2017-09-20
*/
public interface INoContractDonateService {
    List<NoContractDonateExtension> GetRecentNoContractDonate(int num);

    List<NoContractDonateExtension> GetRecentNoContractDonate(long projectId,int num);
}
