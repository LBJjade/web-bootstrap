package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.contract.NoContractDonateExtension;
import com.becheer.donation.service.INoContractDonateService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/dontate")
    public Object donate(@RequestBody Map<String, String> params) {
        return null;
    }


}
