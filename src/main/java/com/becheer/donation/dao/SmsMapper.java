package com.becheer.donation.dao;

/*
* SmsMapper
* Creator : xiaokepu
* Date : 2017-09-12
*/

import com.becheer.donation.model.Sms;
import com.becheer.donation.model.extension.sms.SmsTemplateExtension;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface SmsMapper {

    // 根据主键Id获取短信模板
    SmsTemplateExtension selectSmsTemplateById(long smsTemplateId);

    // 根据手机号查询当前手机当天短信总数
    int selectSmsCountByMobile(String mobile);

    // 添加
    int insertSms(Sms sms);

    // 根据手机号查询短信
    Sms selectSmsByMobile(Sms sms);

    // 更新短信状态
    int updateSmsStatus(Sms sms);

    //
    Date selectTimeByMobileAndTemTemplateId(String mobile, long smsTemplateId);
}
