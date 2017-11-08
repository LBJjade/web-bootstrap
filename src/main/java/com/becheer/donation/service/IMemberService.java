package com.becheer.donation.service;

/*
* IMemberService 用户服务接口
* Creator : xiaokepu
* Date : 
*/

import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberInfoExtension;

public interface IMemberService {
    ResponseDto SubmitRegister(String mobile,String pwd,int role);

    ResponseDto Login(String mobile,String pwd);

    ResponseDto GetMemberById(long memberId);

    Member GetMemberByMobile(String mobile);

    Member GetMember(long memberId);

    MemberInfoExtension GetMemberExtensionById(long memberId);

    ResponseDto UpdateMemberInfo(MemberInfoExtension memberInfoExtensions);

    int UpdatePw(String newPw,String mobile);
}
