package com.becheer.donation.service;

import com.becheer.donation.model.Accepter;
import com.becheer.donation.model.base.ResponseDto;

/*
* IAccepterService 受捐人Service类接口
* Creator : xiaokepu
* Date : 2017-11-23
*/
public interface IAccepterService {
    ResponseDto login(String authNo,String pwd);

    ResponseDto getAccepterInfo(long aid);
}
