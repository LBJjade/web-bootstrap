package com.becheer.donation.dao;

import com.becheer.donation.model.extension.allocate.AllocatePlanExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* AllocatePlanMapper
* Creator : xiaokepu
* Date : 2017-11-29
*/

@Component
public interface AllocatePlanMapper {
    List<AllocatePlanExtension> selectAllocatePlanByContractId(long contractId);

    List<AllocatePlanExtension> selectAllocatePlanByMemberId(long memberId);
}
