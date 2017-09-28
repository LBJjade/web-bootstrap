package com.becheer.donation.service;

/*
* IIntentionService
* Creator : xiaokepu
* Date : 2017-09-28
*/

import com.becheer.donation.model.Intention;
import com.github.pagehelper.PageInfo;

public interface IIntentionService {
    PageInfo<Intention> GetIntentionList(long memberId, int pageNum, int pageSize);
}
