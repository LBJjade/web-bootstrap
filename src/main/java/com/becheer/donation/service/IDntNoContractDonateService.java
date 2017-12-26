package com.becheer.donation.service;

/*
* IDntNoContractDonateService 用户服务接口
* Date :
*/

import com.becheer.donation.model.DntNoContractDonate;

public interface IDntNoContractDonateService {

    int insert(DntNoContractDonate dntNoContractDonate);

    int update(DntNoContractDonate dntNoContractDonate);

    long selectProjectIdById(Integer id);

    Long selectMemberIdById(Integer id);
}
