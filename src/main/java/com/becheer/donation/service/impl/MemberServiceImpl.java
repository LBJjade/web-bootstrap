package com.becheer.donation.service.impl;

/*
* MemberService
* Creator : xiaokepu
* Date : 2017-09-14
*/

import com.becheer.donation.dao.MemberMapper;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.HashUtil;
import com.becheer.donation.utils.UUID;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class MemberServiceImpl implements IMemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public ResponseDto SubmitRegister(String registerInfo, String pwd) {
        Member member = new Member();
        member.setMobile(registerInfo.split("|")[0]);
        member.setRole(Integer.parseInt(registerInfo.split("|")[1]));
        member.setPassword(pwd);
        member.setEnable(1);
        member.setCreateTime(new Date());
        member.setPassword(HashUtil.GetPassword(pwd));
        member.setId(UUID.GetInt64UUID());
        int result = memberMapper.insertMember(member);
        if (result==1){
            return new ResponseDto(200,"注册成功");
        }else{
            return new ResponseDto(400,"注册失败，请重试");
        }
    }

    @Override
    public ResponseDto Login(String mobile,String pwd) {
         Member member = memberMapper.selectMemberByMobile(mobile);
         if (member==null){
             return new ResponseDto(404, Message.LOGIN_MOBILE_NOT_EXIST);
         }
         if (member.getEnable()==0){
             return new ResponseDto(405,Message.LOGIN_ACCOUNT_DISABLED);
         }
         if (!member.getPassword().equals(HashUtil.GetPassword(pwd))){
             return new ResponseDto(406,Message.LOGIN_PASSWORD_ERROR);
         }else{
             return new ResponseDto(407,Message.LOGIN_SUCCESS);
         }
    }
}
