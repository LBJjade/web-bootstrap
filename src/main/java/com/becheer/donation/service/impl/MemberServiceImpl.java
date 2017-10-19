package com.becheer.donation.service.impl;

/*
* MemberService
* Creator : xiaokepu
* Date : 2017-09-14
*/

import com.becheer.donation.dao.MemberMapper;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberInfoExtension;
import com.becheer.donation.service.IMemberService;
import com.becheer.donation.strings.ConstString;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.HashUtil;
import com.becheer.donation.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MemberServiceImpl implements IMemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public ResponseDto SubmitRegister(String mobile, String pwd, int role) {
        Member member = new Member();
        member.setMobile(mobile);
        member.setRole(role);
        member.setPassword(pwd);
        member.setEnable(1);
        member.setPassword(HashUtil.GetPassword(pwd));
//        member.setId(UUID.GetInt64UUID());
        int result = memberMapper.insertMember(member);
        if (result==1){
            return new ResponseDto(200,"注册成功");
        }else{
            return new ResponseDto(400,"注册失败，请重试");
        }
    }

    @Override
    public ResponseDto Login(String mobile,String pwd) {
         Member member = memberMapper.SelectMemberByMobile(mobile);
         if (member==null){
             return new ResponseDto(404, Message.LOGIN_MOBILE_NOT_EXIST);
         }
         if (member.getEnable()==0){
             return new ResponseDto(405,Message.LOGIN_ACCOUNT_DISABLED);
         }
         if (!member.getPassword().equals(HashUtil.GetPassword(pwd))){
             return new ResponseDto(406,Message.LOGIN_PASSWORD_ERROR);
         }else{
             return new ResponseDto(407,Message.LOGIN_SUCCESS,member);
         }
    }

    @Override
    public ResponseDto GetMemberById(long memberId) {
        Member member = memberMapper.SelectMemberById(memberId);
        if (member==null){
            return new ResponseDto(400, Message.MEMBER_NOT_EXISTS);
        }
        if (member.getEnable()==0){
            return new ResponseDto(401,Message.MEMBER_DISABLED);
        }

        MemberInfoExtension memberInfoExtension=new MemberInfoExtension();
        memberInfoExtension.setId(member.getId());
        memberInfoExtension.setName(member.getMemberName());
        memberInfoExtension.setRole(member.getRole());
        memberInfoExtension.setProject(member.getProject());
        memberInfoExtension.setSummary(member.getSummary());
        memberInfoExtension.setValidation(member.getValidation());
        memberInfoExtension.setAvator(member.getAvatorImg());
        memberInfoExtension.setBirthday(member.getBirthday());
        if (member.getRole()==0){
            //个人
            memberInfoExtension.setMobile(member.getMobile());
            memberInfoExtension.setIdCard(member.getIdCard());
            memberInfoExtension.setSex(member.getSex());
            memberInfoExtension.setBirthday(member.getBirthday());
        }else if (member.getRole()==1){
            //公司
            memberInfoExtension.setOrganizationType(member.getOrganizationType());
            memberInfoExtension.setOrganizationCode(member.getOrganizationCode());
            memberInfoExtension.setLicense(member.getLicense());
        }else{
            return new ResponseDto(404,Message.MEMBER_ROLE_ERROR);
        }
        return new ResponseDto(200,Message.MEMBER_GET_SUCCESS,memberInfoExtension);
    }

    @Override
    public Member GetMemberByMobile(String mobile) {
        return memberMapper.SelectMemberByMobile(mobile);
    }

    @Override
    public MemberInfoExtension GetMemberExtensionById(long memberId) {
        Member member = memberMapper.SelectMemberById(memberId);
        if (member==null){
            return null;
        }
        MemberInfoExtension memberInfoExtension=new MemberInfoExtension();
        memberInfoExtension.setId(member.getId());
        memberInfoExtension.setName(member.getMemberName());
        memberInfoExtension.setRole(member.getRole());
        memberInfoExtension.setProject(member.getProject());
        memberInfoExtension.setSummary(member.getSummary());
        memberInfoExtension.setValidation(member.getValidation());
        memberInfoExtension.setAvator(member.getAvatorImg());
        memberInfoExtension.setBirthday(member.getBirthday());
        if (member.getRole()==0){
            //个人
            memberInfoExtension.setMobile(member.getMobile());
            memberInfoExtension.setIdCard(member.getIdCard());
            memberInfoExtension.setSex(member.getSex());
            memberInfoExtension.setBirthday(member.getBirthday());
        }else if (member.getRole()==1){
            //公司
            memberInfoExtension.setOrganizationType(member.getOrganizationType());
            memberInfoExtension.setOrganizationCode(member.getOrganizationCode());
            memberInfoExtension.setLicense(member.getLicense());
        }
        return memberInfoExtension;
    }

    @Override
    public ResponseDto UpdateMemberInfo(MemberInfoExtension memberInfoExtension) {
        Member member = new Member();
        member.setId(memberInfoExtension.getId());
        member.setMemberName(memberInfoExtension.getName());
        member.setSex(memberInfoExtension.getSex());
        member.setBirthday(memberInfoExtension.getBirthday());
        member.setProject(memberInfoExtension.getProject());
        member.setIdCard(memberInfoExtension.getIdCard());
        member.setSummary(memberInfoExtension.getSummary());
        member.setValidation(memberInfoExtension.getValidation());
        int result = memberMapper.UpdateMember(member);
        if (result>0){
            RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY+":"+ConstString.TABLE_MEMBER+":"+member.getId());
            return new ResponseDto(200,Message.MEMBER_UPDATE_SUCCESS);
        }else{
            return new ResponseDto(500,Message.MEMBER_UPDATE_ERROR);
        }
    }
}
