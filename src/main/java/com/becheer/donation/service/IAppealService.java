package com.becheer.donation.service;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.github.pagehelper.PageInfo;

/*
* IAppealService
* Creator : xiaokepu
* Date : 2017-10-11
*/
public interface IAppealService {
    PageInfo<MemberAppealExtension> GetMemberAppeal(long memberId, int pageNum, int pageSize);

    MemberAppealDetailExtension GetMemberAppealDetail(long appealId, long memberId);

    ResponseDto InsertAppeal(String title, String method, String content, long contractId, String contractNo, long projectId, long memberId, int appealType, long accepterId);

    ResponseDto UpdateAppealStatus(long appealId, int status);

    PageInfo<MemberAppealExtension> getAccepterAppeal(long memberId, int pageNum, int pageSize);

    MemberAppealDetailExtension getAccepterAppealDetail(long appealId, long memberId);

    ResponseDto solveAppeal(long memberId,long appealId,int status);

    ResponseDto withdrawAppeal(long memberId,long appealId,int status);
}
