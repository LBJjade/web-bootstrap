package com.becheer.donation.controller;

import com.becheer.core.support.pay.WxPay;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.model.extension.project.ListProjectTypeExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.becheer.donation.service.IProjectService;
import com.becheer.donation.service.IProjectTypeService;
import com.becheer.donation.service.IWxPayService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*
* WxPayController 微信支付控制器
* Date : 2017-10-21
*/

@Controller
@RequestMapping("/wxpay")
public class WxPayController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

    @Resource
    private IWxPayService wxPayService;

    @GetMapping("")
    public String View(HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("pay/wxpay");
    }

    @PostMapping("/pay")
    @ResponseBody
    public ResponseDto pay(@RequestBody Map<String, String> requestBody) {
        try {
            String productId = requestBody.get("productId");
            String outTradeNo = requestBody.get("orderNo");
            String totalFee = requestBody.get("amount");

            String result = productId;
            // WxPay.unifiedOrder();
            return new ResponseDto(200, Message.WXPAY_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("WxPay", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
