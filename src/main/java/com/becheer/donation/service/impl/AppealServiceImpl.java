package com.becheer.donation.service.impl;

/*
* AppealServiceImpl
* Creator : xiaokepu
* Date : 2017-10-11
*/

import com.becheer.donation.dao.AppealMapper;
import com.becheer.donation.dao.ProgressMapper;
import com.becheer.donation.model.Progress;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.appeal.MemberAppealDetailExtension;
import com.becheer.donation.model.extension.appeal.MemberAppealExtension;
import com.becheer.donation.model.extension.appeal.AppealDetailExtension;
import com.becheer.donation.service.IAppealService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppealServiceImpl implements IAppealService {

    @Resource
    AppealMapper appealMapper;

    @Resource
    ProgressMapper progressMapper;

    @Override
    public PageInfo<MemberAppealExtension> GetMemberAppeal(long memberId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MemberAppealExtension> data = appealMapper.SelectAppealByMemberId(memberId);
        PageInfo<MemberAppealExtension> pageInfo = new PageInfo<MemberAppealExtension>(data);
        return pageInfo;
    }

    @Override
    public MemberAppealDetailExtension GetMemberAppealDetail(long appealId, long memberId) {
        return appealMapper.SelectAppealDetail(appealId, memberId);
    }

    @Override
    public ResponseDto InsertAppeal(String title, String method, String content, long contractId, String contractNo, long projectId, long memberId, int appealType, long accepterId) {
        AppealDetailExtension appealdetail = new AppealDetailExtension();
        appealdetail.setAppealTitle(title);
        appealdetail.setAppealMethod(method);
        appealdetail.setAppealContent(content);
        appealdetail.setContractId(contractId);
        appealdetail.setProjectId(projectId);
        appealdetail.setMemberId(memberId);
        appealdetail.setAppealType(appealType);
        appealdetail.setAccepterId(accepterId);
        appealMapper.InsertAppeal(appealdetail);
        if (appealdetail.getId() != 0) {
            if (appealType==1) {
                Progress progress = new Progress();
                progress.setRefTable("dnt_member");
                progress.setRefRecordId(memberId);
                progress.setType(3);
                progress.setEnable(1);
                String progressTitle = "会员提交了申诉，申诉合同编号：" + contractNo;
                progress.setTitle(progressTitle);
                progress.setContent(progressTitle);
                progress.setProgressType(3);
                progressMapper.InsertProgress(progress);
            }
            return new ResponseDto(200, Message.SUBMIT_APPEAL_SUCCESS, appealdetail.getId());
        } else {
            return new ResponseDto(500, Message.SUBMIT_APPEAL_FAILED);
        }
    }

    @Override
    public ResponseDto UpdateAppealStatus(long appealId, int status) {
        int result = appealMapper.updateAppealStatus(appealId, status);
        if (result > 0) {
            RedisUtil.delAppealkey(appealId);
            return new ResponseDto(200, Message.ACTION_SUCCESS);
        } else {
            return new ResponseDto(500, Message.ACTION_FAILED);
        }
    }

    @Override
    public PageInfo<MemberAppealExtension> getAccepterAppeal(long memberId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MemberAppealExtension> data = appealMapper.selectAccepterAppealByMemberId(memberId);
        PageInfo<MemberAppealExtension> pageInfo = new PageInfo<MemberAppealExtension>(data);
        return pageInfo;
    }

    @Override
    public MemberAppealDetailExtension getAccepterAppealDetail(long appealId, long memberId) {
        return appealMapper.selectAccepterAppealDetail(appealId, memberId);
    }

    @Override
    public ResponseDto solveAppeal(long memberId, long appealId, int status) {
        int result = appealMapper.updateAppealStatus(appealId, status);
        if (result > 0) {
            Progress progress = new Progress();
            progress.setRefTable("dnt_member");
            progress.setRefRecordId(memberId);
            progress.setType(3);
            progress.setEnable(1);
            String progressTitle = "会员将申诉标识为\"已解决\"";
            progress.setTitle(progressTitle);
            progress.setContent(progressTitle);
            progress.setProgressType(3);
            progressMapper.InsertProgress(progress);
            RedisUtil.delAppealkey(appealId);
            return new ResponseDto(200, Message.ACTION_SUCCESS);
        } else {
            return new ResponseDto(500, Message.ACTION_FAILED);
        }
    }

    @Override
    public ResponseDto withdrawAppeal(long memberId, long appealId, int status) {
        int result = appealMapper.updateAppealStatus(appealId, status);
        if (result > 0) {
            Progress progress = new Progress();
            progress.setRefTable("dnt_member");
            progress.setRefRecordId(memberId);
            progress.setType(3);
            progress.setEnable(1);
            String progressTitle = "会员撤回了申诉";
            progress.setTitle(progressTitle);
            progress.setContent(progressTitle);
            progress.setProgressType(3);
            progressMapper.InsertProgress(progress);
            RedisUtil.delAppealkey(appealId);
            return new ResponseDto(200, Message.ACTION_SUCCESS);
        } else {
            return new ResponseDto(500, Message.ACTION_FAILED);
        }
    }
}
