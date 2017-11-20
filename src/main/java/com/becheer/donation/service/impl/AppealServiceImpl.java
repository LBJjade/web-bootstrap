package com.becheer.donation.service.impl;

/*
* AppealServiceImpl
* Creator : xiaokepu
* Date : 2017-10-11
*/

import com.becheer.donation.dao.AppealMapper;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.becheer.donation.model.extension.appeal.AppealDetailExtension;
import com.becheer.donation.service.IAppealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppealServiceImpl implements IAppealService {

    @Resource
    AppealMapper appealMapper;

    @Override
    public PageInfo<MemberAppealExtension> GetMemberAppeal(long memberId,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<MemberAppealExtension> data=appealMapper.SelectAppealByMemberId(memberId);
        PageInfo<MemberAppealExtension> pageInfo=new PageInfo<MemberAppealExtension>(data);
        return pageInfo;
    }

    @Override
    public MemberAppealDetailExtension GetMemberAppealDetail(long appealId,long memberId) {
        return appealMapper.SelectAppealDetail(appealId,memberId);
    }

    @Override
    public  void InsertAppeal(String title,String method, String content,long contractProjectId,long projectId,long memberId) {
        AppealDetailExtension appealdetail=new AppealDetailExtension();
        appealdetail.setAppealTitle(title);
        appealdetail.setAppealMethod(method);
        appealdetail.setAppealContent(content);
        appealdetail.setContractId(contractProjectId);
        appealdetail.setProjectId(projectId);
        appealdetail.setMemberId(memberId);
        appealMapper.InsertAppeal(appealdetail);
    }

    @Override
    public ResponseDto UpdateAppealStatus(long appealId, int status) {
        int result = appealMapper.updateAppealStatus(appealId,status);
        if (result>0){

        }else{

        }
        return null;
    }
}
