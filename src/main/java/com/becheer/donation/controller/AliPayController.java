package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAliPayService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.IPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


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

    @ResponseBody
    @PostMapping("/aliQuery")
    public ResponseDto aliQuery(@RequestParam String outTradeNo,@RequestParam String tradeNo) {
        try {
            Map<String, String> result = ailPayService.tradeQuery(outTradeNo,tradeNo);
            return new ResponseDto(200,"");
        } catch (Exception ex) {
            LOGGER.error("aliQuery", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


}
