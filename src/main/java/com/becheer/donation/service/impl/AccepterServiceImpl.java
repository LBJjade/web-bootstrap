package com.becheer.donation.service.impl;

import com.becheer.donation.dao.AccepterMapper;
import com.becheer.donation.model.Accepter;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.accepter.AccepterInfoExtension;
import com.becheer.donation.service.IAccepterService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.HashUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
* 
* Creator : xiaokepu
* Date : 
*/
@Service
public class AccepterServiceImpl implements IAccepterService {

    @Resource
    AccepterMapper accepterMapper;

    @Override
    public ResponseDto login(String authNo, String pwd) {
        Accepter accepter = accepterMapper.selectAccepterByNo(authNo);
        if (accepter == null) {
            //未找到受捐人
            return new ResponseDto(400, Message.LOGIN_ACCEPTOR_NOT_EXIST);
        }
        if (accepter.getEnable() == 0) {
            //受捐人已禁用
            return new ResponseDto(400, Message.LOGIN_ACCOUNT_DISABLED);
        }
        if (!accepter.getPassword().equals(HashUtil.GetPassword(pwd))) {
            //密码错误
            return new ResponseDto(400, Message.LOGIN_PASSWORD_ERROR);
        }
        return new ResponseDto(200, Message.LOGIN_SUCCESS, accepter);
    }

    @Override
    public ResponseDto getAccepterInfo(long mid) {
        AccepterInfoExtension accepter = accepterMapper.selectAccepterById(mid);
        if (accepter == null) {
            return new ResponseDto(400, Message.ACCEPTER_NOT_EXISTS);
        }
        if (accepter.getEnable() == 0) {
            return new ResponseDto(400, Message.ACCEPTER_DISABLED);
        }
        return new ResponseDto(200, Message.ACCEPTER_GET_SUCCESS, accepter);
    }

    @Override
    public AccepterInfoExtension getAccepterByMemberId(long mid){
        return accepterMapper.selectAccepterById(mid);
    }
}
