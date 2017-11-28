package com.becheer.donation.service;

/*
* IIntentionService
* Creator : xiaokepu
* Date : 2017-09-28
*/

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.intention.IntentionExtension;
import com.github.pagehelper.PageInfo;

public interface IIntentionService {
    PageInfo<Intention> GetIntentionList(long memberId, int pageNum, int pageSize);

    IntentionExtension GetIntention(long intentionId);

    ResponseDto AddIntention(Intention intention);

    String generateContractNo();

}
