package com.becheer.donation.utils;

import com.alibaba.fastjson.JSONObject;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.strings.ConstString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
* HttpUtil HTTP工具
* Creator : xiaokepu
* Date : 2017-09-27
*/

public class HttpUtil {

    //获取当前登录用户
    public static MemberSessionExtension GetCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        JSONObject sessionObject=(JSONObject)session.getAttribute(ConstString.MEMBER_SESSION_CODE);

        String a=sessionObject.get("mobile").toString();
        MemberSessionExtension result=new MemberSessionExtension();
        result.setMemberId(sessionObject.getIntValue("memberId"));
        result.setMobile(sessionObject.getString("mobile"));
        result.setValidation(sessionObject.getIntValue("validation"));
        result.setMemberName(sessionObject.getString("memberName"));
        result.setRole(sessionObject.getIntValue("role"));
        return result;
    }
}
