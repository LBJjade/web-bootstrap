package com.becheer.donation.service;

/*
* ISmsService 短信服务类接口
* Creator : xiaokepu
* Date : 2017-09-12
*/

import com.becheer.donation.model.base.ResponseDto;

public interface ISmsService {
    ResponseDto SendSms(String mobile, long templateId);

    ResponseDto CheckLoginCode(String mobile, String code);

    ResponseDto CheckCode(String mobile, String code, long templateId);
}
