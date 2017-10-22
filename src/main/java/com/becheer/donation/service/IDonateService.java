package com.becheer.donation.service;

import com.becheer.core.support.pay.WxPay;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;

public interface IDonateService {

    /**
     * 根据主键id获取项目
     */
    public WxPayPrepayExtension donate(Donate donate);
}
