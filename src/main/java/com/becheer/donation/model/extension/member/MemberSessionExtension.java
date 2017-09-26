package com.becheer.donation.model.extension.member;

/*
* MemberSessionExtension 会员登录Session
* Creator : xiaokepu
* Date : 2017-09-26
*/

public class MemberSessionExtension {
    private long memberId;

    private String memberName;

    private String mobile;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
