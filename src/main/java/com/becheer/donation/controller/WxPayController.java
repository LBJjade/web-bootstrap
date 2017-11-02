package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IWxPayService;
import com.becheer.donation.strings.Message;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping("/status")
    @ResponseBody
    public ResponseDto status(@RequestParam String orderNo) {
        Map map = wxPayService.status(orderNo);

        if (map == null) {
            return new ResponseDto(404, Message.WXPAY_GET_STATUS_ORDER_NO_NO_EXISTS);
        }

        String notify_return_code = null;
        if (map.containsKey("notify_return_code")) {
            notify_return_code = map.get("notify_return_code").toString();
        }
        if (Strings.isNullOrEmpty(notify_return_code)) {
            return new ResponseDto(204, Message.WXPAY_GET_STATUS_UNPAID);
        }
        return new ResponseDto(200, Message.WXPAY_GET_STATUS_SUCCESS, map);
    }
}
