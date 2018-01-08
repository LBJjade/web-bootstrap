package com.becheer.donation.dao;

/*
* DntNoContractDonateMapper
* Date :
*/

import com.becheer.donation.model.DntNoContractDonate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface DntNoContractDonateMapper {
    int insert(DntNoContractDonate dntNoContractDonate);

    int update(DntNoContractDonate dntNoContractDonate);

    long selectProjectIdById(Integer id);

    Map selectNoContractDonateById(Integer id);

    Long selectMemberIdById(Integer id);

    String generateNo(String iNamePre, String iModule, int iNum, int iNoLength, String oNewNo);
}
