package com.becheer.donation.service;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.accepter.AccepterInfoExtension;

/*
* IAccepterService 受捐人Service类接口
* Creator : xiaokepu
* Date : 2017-11-23
*/
public interface IAccepterService {
    ResponseDto login(String authNo,String pwd);

    ResponseDto getAccepterInfo(long mid);

    public AccepterInfoExtension getAccepterByMemberId(long mid);

    ResponseDto updatePw(String newPw,String acceptCode);

    AccepterInfoExtension getAccepter(Long id);
}
