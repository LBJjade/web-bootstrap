package com.becheer.donation.dao;

import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
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
}
