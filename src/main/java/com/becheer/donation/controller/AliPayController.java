package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IAliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/aliPay")
public class AliPayController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AliPayController.class);

    @Resource
    private IAliPayService ailPayService;

//    @ResponseBody
//    @PostMapping("/aliNotify")
//    public ResponseDto aliNotify(@RequestBody String notifyXML) {
//        try {
//            String result = ailPayService.payNotify(notifyXML);
//            return new ResponseDto(200, result);
//        } catch (Exception ex) {
//            LOGGER.error("aliNotify", ex.getMessage());
//            return new ResponseDto(500, Message.SERVER_ERROR);
//        }
//    }

//    @ResponseBody
//    @PostMapping("/aliQuery")
//    public ResponseDto aliQuery(@RequestParam String outTradeNo,@RequestParam String tradeNo) {
//        try {
//            Map<String, String> result = ailPayService.tradeQuery(outTradeNo,tradeNo);
//            return new ResponseDto(200,"");
//        } catch (Exception ex) {
//            LOGGER.error("aliQuery", ex.getMessage());
//            return new ResponseDto(500, Message.SERVER_ERROR);
//        }
//    }

    //同步返回参数
    //测试例子https://m.alipay.com/GkSL?total_amount=0.10&timestamp=2016-11-02+18%3A34%3A19&sign=G3WI0czviMAOzS5t0fYaDgK32sGpjkkXYVFTpYMtgX8JaXLiGiUTO%2F2IHogcCFT96jBCLZ6IsNzd%2BmxkB%2FRuwG%2F7naQk1qReuORMkrB5cpBf9U40bIUoCmSNqtANsTE2UPV7GKegYG2RqoCRScTmeFAFHj5L7zsM%2BLuYb9mqN3g%3D&trade_no=2016110221001004330228438026&sign_type=RSA2&auth_app_id=2014073000007292&charset=UTF-8&seller_id=2088411964605312&method=alipay.trade.page.pay.return&app_id=2014073000007292&out_trade_no=20150g320g010101001&version=1.0
    @ResponseBody
    @GetMapping("/aliReturn")
    public ResponseDto aliReturn(@RequestBody String returnXML) {

        return null;
    }

    //异步返回参数
    @ResponseBody
    @PostMapping("/aliNotify")
    public ResponseDto aliNotify(HttpServletRequest request,@RequestBody String notifyXML) {
        return ailPayService.signVerfied(request,notifyXML);
//        return null;
    }




}
