package com.becheer.donation.controller;

import com.becheer.core.support.pay.WxPayHelper;
import com.becheer.core.util.ImageUtil;
import com.becheer.core.util.QRCodeUtil;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.NoContractDonateExtension;
import com.becheer.donation.model.extension.donate.Donate;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.wxpay.WxPayPrepayExtension;
import com.becheer.donation.service.IDonateService;
import com.becheer.donation.service.INoContractDonateService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.IPUtil;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private IDonateService donateService;

    @Resource
    private INoContractDonateService noContractDonateService;

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
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/donate")
    // public Object donate(HttpServletRequest request, @RequestBody Donate donate) {
    public Object donate(HttpServletRequest request, @RequestParam int projectTypeId, @RequestParam int projectId, @RequestParam int amount) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
//            if (currentMember == null) {
//                MemberAuthFailed();
//            }
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

//            Integer projectTypeId = donate.getProjectTypeId();
//            Integer projectId = donate.getProjectId();
//            Integer amount = donate.getAmount();

//            if (projectTypeId == null) {
//                return new ResponseDto(400, Message.DONATE_PROJECT_TYPE_ID_IS_EMPTY);
//            }
//
//            if (projectId == null) {
//                return new ResponseDto(400, Message.DONATE_PROJECT_ID_BAD_REQUEST);
//            }
//
//            if (amount == null) {
//                return new ResponseDto(400, Message.DONATE_AMOUNT_IS_EMPTY);
//            }

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
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }


}
