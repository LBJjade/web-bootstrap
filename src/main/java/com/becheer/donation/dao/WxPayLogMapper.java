package com.becheer.donation.dao;

import com.becheer.donation.model.WxPayLog;

import java.util.List;

public interface WxPayLogMapper {
    List<WxPayLog> selectWxPayLogById(int id);
    int update(WxPayLog id);
    int insert(WxPayLog wxPayLog);
}
