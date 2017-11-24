package com.becheer.donation.service;

import com.becheer.core.support.pay.WxPay;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.donate.DonateContract;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;

import java.util.Map;

public interface IDonateService {

    /**
     * 根据主键id获取项目
     */
    public Map<String, String> donate(Donate donate, String ip, String memberName);


    /**
     * 根据主键id获取合同项目
     */
    public Map<String, String> donateContract(Long memberId, String ip,Long paymentPlanId);


}
