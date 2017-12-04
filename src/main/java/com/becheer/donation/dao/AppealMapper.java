package com.becheer.donation.dao;

import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.becheer.donation.model.extension.appeal.AppealDetailExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* AppealMapper
* Creator : xiaokepu
* Date : 2017-10-11
*/

@Component
public interface AppealMapper {

    List<MemberAppealExtension> SelectAppealByMemberId(long memberId);

    MemberAppealDetailExtension SelectAppealDetail(long appealId,long memberId);

    void InsertAppeal( AppealDetailExtension appealdetail);

    int updateAppealStatus(long appealId,int status);

    List<MemberAppealExtension> selectAccepterAppealByMemberId(long memberId);

    MemberAppealDetailExtension selectAccepterAppealDetail(long appealId,long memberId);
}
