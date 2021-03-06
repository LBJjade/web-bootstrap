package com.becheer.donation.controller;

import com.becheer.core.support.pay.WxPayHelper;
import com.becheer.core.util.ImageUtil;
import com.becheer.core.util.QRCodeUtil;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.NoContractDonateExtension;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.*;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.IPUtil;
import com.becheer.donation.utils.RegExUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* DonateController 捐赠控制器
* Creator : xiaokepu
* Date : 
*/
@Controller
@RequestMapping("/donate")
public class DonateController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonateController.class);

    @Resource
    private IDonateService donateService;

    @Resource
    private INoContractDonateService noContractDonateService;

    @Resource
    private IMemberService memberService;

    @Resource
    private ISmsService smsService;

    @Resource
    IPaymentPlanService paymentPlanService;

    @ResponseBody
    @PostMapping("/recent")
    public ResponseDto<List<NoContractDonateExtension>> GetRecentDonate(HttpServletRequest request, @RequestParam int num) {
        if (num > 20 || num < 1) {
            num = 8;
        }
        try {
            List<NoContractDonateExtension> resultList = noContractDonateService.GetRecentNoContractDonate(num);
            return new ResponseDto(200, Message.NOCONTRACT_GET_RECENT_SUCCESS, resultList);
        } catch (Exception ex) {
            LOGGER.error("GetRecentDonate", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/project")
    public ResponseDto<List<NoContractDonateExtension>> GetRecentDonate(HttpServletRequest request, @RequestParam int num, @RequestParam long projectId) {
        if (num > 20 || num < 1) {
            num = 8;
        }
        try {
            List<NoContractDonateExtension> resultList = noContractDonateService.GetRecentNoContractDonate(projectId, num);
            return new ResponseDto(200, Message.NOCONTRACT_GET_RECENT_SUCCESS, resultList);
        } catch (Exception ex) {
            LOGGER.error("GetRecentDonate", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/donate")
    // public Object donate(HttpServletRequest request, @RequestBody Donate donate) {
    public Object donate(HttpServletRequest request, @RequestParam int projectTypeId, @RequestParam int projectId, @RequestParam int amount) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);

            Long memberId = null; // currentMember.getMemberId();
            String memberName = null;
            if (currentMember != null) {
                memberId = currentMember.getMemberId();
                memberName = currentMember.getMemberName();
            }
            Donate donate = new Donate();
            donate.setMemberId(memberId);
            donate.setProjectTypeId(projectTypeId);
            donate.setProjectId(projectId);
            donate.setAmount(amount);


            String ip = IPUtil.getIpAddress(request);
            Map<String, String> map = donateService.donate(donate, ip, memberName);


            Map<String, String> prepay = new HashMap<>();
            String returnCode = map.get("return_code");
            String resultCode = map.get("result_code");
            String qrCodeURL = null;
            if (WxPayHelper.codeIsOK(returnCode) && WxPayHelper.codeIsOK(resultCode)) {
                // responsedTradeType = wxPayResponse.get("trade_type");
                // preparedId = wxPayResponse.get("prepared_id");
                qrCodeURL = map.get("code_url");
                if (qrCodeURL != null) {
                    String qrCodeImageBase64 = ImageUtil.encodeBufferedImageToBase64(QRCodeUtil.createQRCode(qrCodeURL, 300, 300), "png");
                    prepay.put("qrCodeImageBase64", qrCodeImageBase64);
                }
                String orderNo = map.get("orderNo");
                prepay.put("orderNo", orderNo);
            }


            return new ResponseDto(200, Message.NOCONTRACT_GET_RECENT_SUCCESS, prepay);
        } catch (Exception ex) {
            LOGGER.error("donate", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    /**
     * 合同捐赠
     */
    @ResponseBody
    @PostMapping("/donateContract")
    // public Object donate(HttpServletRequest request, @RequestBody Donate donate) {
    public Object donateContract(HttpServletRequest request, @RequestParam Long paymentPlanId) {
        try {
            //验证是否登陆
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                MemberAuthFailed();
            }
            //得到当前用户与用户名
            Long memberId = currentMember.getMemberId();
            String memberName = currentMember.getMemberName();
            String ip = IPUtil.getIpAddress(request);
            ////
            Map<String, String> map = donateService.donateContract(memberId, ip, paymentPlanId);
//            Map<String, String> map = donateService.donateContract(donateContract, ip, memberName,paymentPlanId);
            if (map == null) {
                return new ResponseDto(501, Message.CONTRACT_HAD_DONATE);
            } else {
                Map<String, String> prepay = new HashMap<>();
                String returnCode = map.get("return_code");
                String resultCode = map.get("result_code");
                String qrCodeURL = null;
                if (WxPayHelper.codeIsOK(returnCode) && WxPayHelper.codeIsOK(resultCode)) {
                    // responsedTradeType = wxPayResponse.get("trade_type");
                    // preparedId = wxPayResponse.get("prepared_id");
                    qrCodeURL = map.get("code_url");
                    if (qrCodeURL != null) {
                        String qrCodeImageBase64 = ImageUtil.encodeBufferedImageToBase64(QRCodeUtil.createQRCode(qrCodeURL, 300, 300), "png");
                        prepay.put("qrCodeImageBase64", qrCodeImageBase64);
                    }
                    String orderNo = map.get("orderNo");
                    String amount = map.get("amount");
                    prepay.put("orderNo", orderNo);
                    prepay.put("amount", amount);
                }


                return new ResponseDto(200, Message.NOCONTRACT_GET_RECENT_SUCCESS, prepay);
            }
        } catch (Exception ex) {
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    //大额捐赠验证码
    @PostMapping(value = "/SendSms")
    @ResponseBody
    public ResponseDto SendSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam long tid) {
        try {
            if (!RegExUtil.checkMobile(mobile)) {
                return new ResponseDto(400, Message.VALIDATION_MOBILE_FAILED);
            }
            Member member = memberService.GetMemberByMobile(mobile);
            if (member == null) {
                return new ResponseDto(401, Message.REGISTER_NO_EXIST);
            }
            return smsService.SendSms(mobile, tid);
        } catch (Exception ex) {
            LOGGER.error("SendSms", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


    /**
     * 短信验证
     */
    @PostMapping(value = "/validate")
    @ResponseBody
    public ResponseDto CheckSms(HttpServletRequest request, @RequestParam String mobile, @RequestParam String code, @RequestParam Long tid) {
        try {
            //根据手机验证账号
            Member member = memberService.GetMemberByMobile(mobile);
            int enable = member.getEnable();
            if (enable == 0) {
                return new ResponseDto(402, Message.MEMBER_UNENABLE);
            } else {
                //验证短信
                ResponseDto result = smsService.CheckCode(mobile, code, tid);
                return result;
            }
        } catch (Exception ex) {
            LOGGER.error("CheckSms", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    //支付宝支付
    @ResponseBody
    @PostMapping("/aliPayDonate")
    public ResponseDto aliPayDonate(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @RequestParam int projectTypeId, @RequestParam int projectId, @RequestParam int amount) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(httpRequest);

            Long memberId = null;
            String memberName = null;
            if (currentMember != null) {
                memberId = currentMember.getMemberId();
                memberName = currentMember.getMemberName();
            }
            Donate donate = new Donate();
            donate.setMemberId(memberId);
            donate.setProjectTypeId(projectTypeId);
            donate.setProjectId(projectId);
            donate.setAmount(amount);
            String ip = IPUtil.getIpAddress(httpRequest);

//            Map<String, String> map = ailPayService.pay(donate, ip, memberName);
            Map<String, String> map = donateService.aliPaydonate(httpRequest,httpResponse,donate, ip, memberName);


        } catch (Exception e) {
            return null;
        }

        return null;
    }

    @ResponseBody
    @PostMapping("/aliPayDontractDonate")
    public ResponseDto aliPayDontractDonate() {
        return null;
    }

    @ResponseBody
    @PostMapping("/aliNotify")
    public ResponseDto aliNotify(@RequestBody String notifyXML) {
        return null;
    }


}
