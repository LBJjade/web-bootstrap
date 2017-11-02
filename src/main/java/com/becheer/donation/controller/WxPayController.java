package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IWxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public ResponseDto status(@RequestBody String orderNo) {
        String result = wxPayService.status(orderNo);

        return new ResponseDto(200, result);
    }
}
