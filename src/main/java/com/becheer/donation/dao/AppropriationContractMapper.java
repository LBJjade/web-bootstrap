package com.becheer.donation.dao;

/*
* AppropriationContractMapper
* Creator : xiaokepu
* Date : 2017-11-28
*/

import com.becheer.donation.model.extension.contract.AppropriationContractExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppropriationContractMapper {
    List<AppropriationContractExtension> selectAccepterContractList(long accepterId);
}
