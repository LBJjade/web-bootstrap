package com.becheer.donation.service.impl;

/*
* ContractServiceImpl
* Creator : xiaokepu
* Date : 2017-10-08
*/

import com.becheer.donation.dao.ContractMapper;
import com.becheer.donation.dao.ProgressMapper;
import com.becheer.donation.model.Progress;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.MemberContractContentExtension;
import com.becheer.donation.model.extension.contract.MemberContractDetailExtension;
import com.becheer.donation.model.extension.contract.MemberContractExtension;
import com.becheer.donation.service.IContractService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContractServiceImpl implements IContractService {

    @Resource
    ProgressMapper progressMapper;

    @Resource
    ContractMapper contractMapper;

    @Override
    public PageInfo<MemberContractExtension> GetContractList(long memberId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MemberContractExtension> data = contractMapper.SelectContractByMemberId(memberId);
        PageInfo<MemberContractExtension> pageInfo = new PageInfo<>(data);
        return pageInfo;
    }

    @Override
    public MemberContractDetailExtension GetMemberContractDetail(long contractId) {
        return contractMapper.SelectContractDetail(contractId);
    }

    @Override
    public MemberContractContentExtension GetContractContent(long contractId) {
        return contractMapper.SelectContractContent(contractId);
    }

    @Override
    public ResponseDto signContract(long contractId, long memberId,String imgArray) {
        int result = contractMapper.UpdateContractStatus(contractId, memberId);
        if (result > 0) {
            MemberContractDetailExtension contract=contractMapper.selectContractByContractProjectId(contractId);
            Progress progress=new Progress();
            progress.setRefTable("dnt_member");
            progress.setRefRecordId(memberId);
            progress.setType(3);
            progress.setEnable(1);
            String title ="会员签订了合同，合同编号："+contract.getContractNo();
            progress.setTitle(title);
            progress.setContent(title);
            progress.setProgressType(3);
            progressMapper.InsertProgress(progress);

            return new ResponseDto(200, Message.CONTRACT_SIGN_SUCCESS);
        } else {
            return new ResponseDto(500, Message.CONTRACT_SIGN_FAILED);
        }
    }

    @Override
    public MemberContractDetailExtension getContractByContractProjectId(long contractProjectId) {
        return contractMapper.selectContractByContractProjectId(contractProjectId);
    }

    @Override
    public Long selectMemberIdById(Integer id) {
        return contractMapper.selectMemberIdById(id);
    }
}
