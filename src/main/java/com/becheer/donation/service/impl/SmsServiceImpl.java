package com.becheer.donation.service.impl;

/*
* SmsServiceImpl 短信服务类
* Creator : xiaokepu
* Date : 2017-09-12
*/

import com.becheer.donation.configs.SmsConfig;
import com.becheer.donation.dao.SmsMapper;
import com.becheer.donation.model.Sms;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.sms.SmsResponse;
import com.becheer.donation.model.extension.sms.SmsTemplateExtension;
import com.becheer.donation.service.ISmsService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.DateUtils;
import com.becheer.donation.utils.SmsUtil;
import com.becheer.donation.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SmsServiceImpl implements ISmsService {
    @Resource
    private SmsMapper smsMapper;

    @Autowired
    SmsConfig smsConfig;


    //发送短信
    @Override
    public ResponseDto SendSms(String mobile, long templateId) {
        //检查短信模板是否存在
        SmsTemplateExtension smsTemplateExtension = smsMapper.selectSmsTemplateById(templateId);
        if (smsTemplateExtension == null) {
            //短信模板无效
            return new ResponseDto(400, Message.REGISTER_SMS_INDIVIDUAL_TEMPLATE);
        }
        //检查当前手机已发送短信数量
        int totalSms = smsMapper.selectSmsCountByMobile(mobile);
        if (totalSms >= 30) {
            //当日短信用尽
            return new ResponseDto(401, Message.REGISTER_SMS_USE_OUT);
        }

        String smsCode = UUID.getRandomNumber(4);

        //验证时间
        Date validTime = smsMapper.selectTimeByMobileAndTemTemplateId(mobile, templateId);
        Date now = new Date();
        if (validTime != null) {
            if (!DateUtils.compareTime(validTime, now)) {
                return new ResponseDto(502, Message.REGISTER_SMS_TIME_OUT);
            }
        }

        Sms sms = new Sms();
        sms.setEnable(1);
        Date date = new Date();
        sms.setCreateTime(date);
        sms.setInvalidTime(DateUtils.dateAdd(6, date, 10));
        sms.setSmsTemplateId(templateId);
        sms.setMobile(mobile);
        sms.setCode(smsCode);
        sms.setText(smsTemplateExtension.getContent().replace("{{code}}", smsCode));
//        sms.setId(UUID.GetInt64UUID());
        SmsResponse sendResult = SmsUtil.SendSms(sms.getMobile(), sms.getText(), smsConfig);
        if (sendResult == null || !sendResult.getStatus().equals("10")) {
            return new ResponseDto(501, Message.REGISTER_SMS_SEND_ERROR);
        }
        sms.setSendErrorCode(sendResult.getStatus());
        sms.setSendErrorMessage(sendResult.getErrorCode());
        int result = smsMapper.insertSms(sms);
        if (result == 1) {
            return new ResponseDto(200, Message.REGISTER_SMS_SEND_SUCCESS);
        } else {
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    //检查验证码是否有效
    @Override
    public ResponseDto CheckLoginCode(String mobile, String code) {
        Sms tempSms = new Sms();
        tempSms.setMobile(mobile);
        tempSms.setSmsTemplateId(1);
        Sms resultSms = smsMapper.selectSmsByMobile(tempSms);
        if (resultSms == null) {
            return new ResponseDto(400, "individual request");
        }
        if (resultSms.getEnable() == 0) {
            return new ResponseDto(401, "invalid sms");
        }
        if (resultSms.getInvalidTime().getTime() < new Date().getTime()) {
            return new ResponseDto(402, "sms is expired");
        }
        if (!resultSms.getCode().equals(code)) {
            return new ResponseDto(403, "code error");
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    resultSms.setEnable(0);
                    smsMapper.updateSmsStatus(resultSms);
                }
            }).start();
            return new ResponseDto(200, "success");
        }
    }


    //检查验证码是否有效
    @Override
    public ResponseDto CheckCode(String mobile, String code, long templateId) {
        Sms tempSms = new Sms();
        tempSms.setMobile(mobile);
        tempSms.setSmsTemplateId(templateId);
        Sms resultSms = smsMapper.selectSmsByMobile(tempSms);
        if (resultSms == null) {
            return new ResponseDto(400, "individual request");
        }
        if (resultSms.getEnable() == 0) {
            return new ResponseDto(401, "invalid sms");
        }
        if (resultSms.getInvalidTime().getTime() < new Date().getTime()) {
            return new ResponseDto(402, "sms is expired");
        }
        if (!resultSms.getCode().equals(code)) {
            return new ResponseDto(403, "code error");
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    resultSms.setEnable(0);
                    smsMapper.updateSmsStatus(resultSms);
                }
            }).start();
            return new ResponseDto(200, "success");
        }
    }

}
