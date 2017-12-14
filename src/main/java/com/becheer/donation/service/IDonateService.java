package com.becheer.donation.service;

import com.becheer.donation.model.extension.donate.Donate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IDonateService {


    /**
     * 微信捐赠
     */
    public Map<String, String> donate(Donate donate, String ip, String memberName);


    /**
     * 微信合同捐赠
     */
    public Map<String, String> donateContract(Long memberId, String ip,Long paymentPlanId);


    /**
     * 支付宝捐赠
     */
    public Map<String, String> aliPaydonate(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Donate donate, String ip, String memberName);


    /**
     * 支付宝合同捐赠
     */
    public Map<String, String> aliPaydonateContract(Long memberId, String ip,Long paymentPlanId);


}
