package com.becheer.donation.service;

import com.becheer.donation.model.extension.allocate.AllocatePlanExtension;

import java.util.List;

/*
* IAllocatePlanService
* Creator : xiaokepu
* Date : 2017-11-29
*/
public interface IAllocatePlanService {

    List<AllocatePlanExtension> getAllocatePlan(Long contractId);

}
