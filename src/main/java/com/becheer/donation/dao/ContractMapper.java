package com.becheer.donation.dao;

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
    List<MemberContractExtension>SelectContractByMemberId(long memberId);
}
