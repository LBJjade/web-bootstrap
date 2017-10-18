package com.becheer.donation.service;

import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.github.pagehelper.PageInfo;

import java.util.List;

/*
* IAppealService
* Creator : xiaokepu
* Date : 2017-10-11
*/
public interface IAppealService {
    PageInfo<MemberAppealExtension> GetMemberAppeal(long memberId,int pageNum,int pageSize);

    MemberAppealDetailExtension GetMemberAppealDetail(long appealId,long memberId);
}