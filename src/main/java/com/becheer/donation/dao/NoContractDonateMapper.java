package com.becheer.donation.dao;

import com.becheer.donation.model.extension.contract.NoContractDonateExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* NoContractDonateMapper
* Creator : xiaokepu
* Date : 2017-09-20
*/
@Component
public interface NoContractDonateMapper {
    List<NoContractDonateExtension> selectRecentNoContractDonate(int num);
}
