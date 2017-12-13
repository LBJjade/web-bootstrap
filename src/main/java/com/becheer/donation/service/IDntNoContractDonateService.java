package com.becheer.donation.service;

/*
* IDntNoContractDonateService 用户服务接口
* Date :
*/

import com.becheer.donation.model.DntNoContractDonate;

public interface IDntNoContractDonateService {

    public int insert(DntNoContractDonate dntNoContractDonate);

    public int update(DntNoContractDonate dntNoContractDonate);

    long selectProjectIdById(Integer id);
}
