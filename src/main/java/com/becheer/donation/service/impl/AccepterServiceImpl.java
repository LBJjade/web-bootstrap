package com.becheer.donation.service.impl;

import com.becheer.donation.dao.AccepterMapper;
import com.becheer.donation.model.Accepter;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IAccepterService;
import com.becheer.donation.utils.HashUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
* 
* Creator : xiaokepu
* Date : 
*/
@Service
public class AccepterServiceImpl implements IAccepterService{

    @Resource
    AccepterMapper accepterMapper;

    @Override
    public ResponseDto login(String authNo, String pwd) {
        Accepter accepter=accepterMapper.selectAccepterByNo(authNo);
        if (accepter==null){
            //未找到受捐人

        }
        if (accepter.getEnable()==0){
            //受捐人已禁用
        }
        if (!accepter.getPassword().equals(HashUtil.GetPassword(pwd))){
            //密码错误
        }
        return null;
    }
}
