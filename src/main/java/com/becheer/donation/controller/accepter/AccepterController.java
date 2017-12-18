package com.becheer.donation.controller.accepter;

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.service.IAccepterService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/*
* AccepterController
* Creator : xiaokepu
* Date : 2017-11-27
*/

@Controller
@RequestMapping("/accepter")
public class AccepterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccepterController.class);

    @Resource
    IAccepterService accepterService;

    @GetMapping(value = "")
    @Access(authorities = {Role.ACCEPTER})
    public String index(HttpServletRequest request) {
        try {
            request.setAttribute("config", fileConfig);
            return this.render("/accepter/index");
        }catch (Exception ex){
            LOGGER.error("index", ex.getMessage());
            return render_500();
        }
    }

    @ResponseBody
    @PostMapping("/info")
    public ResponseDto getAccepterInfo(HttpServletRequest request) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            return accepterService.getAccepterInfo(currentMember.getMemberId());
        }catch (Exception ex){
            LOGGER.error("getAccepterInfo", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
