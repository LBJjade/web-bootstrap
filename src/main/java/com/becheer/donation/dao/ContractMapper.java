package com.becheer.donation.dao;

import com.becheer.donation.model.extension.contract.MemberContractContentExtension;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* ContractMapper
* Creator : xiaokepu
* Date : 2017-10-07
*/
@Component
public interface ContractMapper {
    List<MemberContractExtension> SelectContractByMemberId(long memberId);

    MemberContractDetailExtension SelectContractDetail(long contractId);

    MemberContractContentExtension SelectContractContent(long contractId);

    int UpdateContractStatus(long contractId, long memberId);

    MemberContractDetailExtension selectContractByContractProjectId(long contractProjectId);

    Long selectMemberIdById(Integer Id);

    Integer selectTimeById(Integer id);
}
