package com.becheer.donation.dao;

/*
* MemberMapper
* Creator : xiaokepu
* Date : 2017-09-14
*/

import com.becheer.donation.model.Member;
import org.springframework.stereotype.Component;

@Component
public interface MemberMapper {
    int insertMember(Member member);

    Member selectMemberByMobile(String mobile);
}
