package com.becheer.donation.service;

/*
* IMemberService 用户服务接口
* Creator : xiaokepu
* Date : 
*/

import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;

public interface IMemberService {
    ResponseDto SubmitRegister(String mobile,String pwd);

    ResponseDto Login(String mobile,String pwd);
}
