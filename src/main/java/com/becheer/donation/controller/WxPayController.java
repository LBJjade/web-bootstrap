package com.becheer.donation.controller;

import com.becheer.core.support.pay.WxPayQueryOrderResult;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;
import com.becheer.donation.service.IWxPayService;
import com.becheer.donation.strings.Message;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/notify")
    @ResponseBody
    public ResponseDto notify(@RequestBody String notifyXML) {
        String result = wxPayService.payNotify(notifyXML);
        return new ResponseDto(200, result);
    }

    // @PostMapping("/notify")
    // @ResponseBody
    // public ResponseDto notify(@RequestBody WxPayQueryOrderResult notifyBody) {
    //     String result = wxPayService.payNotify(notifyBody);
    //     return new ResponseDto(200, result);
    // }

    // @PostMapping("/pay")
    // @ResponseBody
    // public ResponseDto pay(@RequestBody Map<String, String> requestBody) {
    //     try {
    //         String projectTypeIdString = requestBody.get("projectTypeId");
    //         String projectIdString = requestBody.get("projectId");
    //         String amountString = requestBody.get("amount");

    //         if (Strings.isNullOrEmpty(projectTypeIdString)) {
    //             return new ResponseDto(400, Message.DONATE_PROJECT_TYPE_ID_IS_EMPTY);
    //         }

    //         Integer projectTypeId = Ints.tryParse(projectIdString);
    //         if (projectTypeId == null) {
    //             return new ResponseDto(400, Message.DONATE_PROJECT_TYPE_ID_BAD_REQUEST);
    //         }

    //         Integer projectId = null;
    //         if (!Strings.isNullOrEmpty(projectIdString)) {
    //             projectId = Ints.tryParse(projectIdString);
    //             if (projectId == null) {
    //                 return new ResponseDto(400, Message.DONATE_PROJECT_ID_BAD_REQUEST);
    //             }
    //         }

    //         if (Strings.isNullOrEmpty(amountString)) {
    //             return new ResponseDto(400, Message.DONATE_AMOUNT_IS_EMPTY);
    //         }

    //         Integer amount = Ints.tryParse(amountString);
    //         if (amount== null) {
    //             return new ResponseDto(400, Message.DONATE_AMOUNT_BAD_REQUEST);
    //         }

    //         WxPayPrepayExtension wxPayPrepayExtension = wxPayService.pay(projectTypeId, projectId, amount);
    //         return new ResponseDto(200, Message.WXPAY_SUCCESS, wxPayPrepayExtension);
    //     } catch (Exception ex) {
    //         LOGGER.error("WxPay", ex);
    //         return new ResponseDto(500, Message.SERVER_ERROR);
    //     }
    // }
}
