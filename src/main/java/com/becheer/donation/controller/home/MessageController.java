package com.becheer.donation.controller.home;

/*
* MessageController
* Creator : xiaokepu
* Date : 2017-09-27
*/

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.message.MessageExtension;
import com.becheer.donation.service.IMessageService;
import com.becheer.donation.strings.Message;
import com.becheer.donation.strings.Role;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/home/message")
public class MessageController extends BaseController {

    @Resource
    private IMessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Access(authorities = {Role.PERSON, Role.COMPANY, Role.ACCEPTER})
    @GetMapping(value = "")
    public String index(javax.servlet.http.HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("home/message");
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetMessage(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum) {
        MemberSessionExtension currentMember = GetCurrentUser(request);
        if (currentMember == null) {
            return MemberAuthFailed();
        }
        if (pageSize < 1 || pageSize > 50) {
            pageSize = 5;
        }
        if (pageNum < 1) {
            pageNum = 1;
        }
        try {
            PageInfo<MessageExtension> result = messageService.GetMessageList(currentMember.getMemberId(), pageNum, pageSize);
            return new ResponseDto(200, Message.MESSAGE_GET_SUCCESS, result);
        } catch (Exception ex) {
            LOGGER.error("GetMessage", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/status")
    @ResponseBody
    public ResponseDto ChangeStatus(HttpServletRequest request, @RequestParam long id) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            long memberId = currentMember.memberId;
            return messageService.ChangeStatus(id, memberId);
        } catch (Exception ex) {
            LOGGER.error("ChangeStatus", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/number")
    @ResponseBody
    public ResponseDto GetMemberMessagesNum(HttpServletRequest request) {
        try {
            MemberSessionExtension currentMember = GetCurrentUser(request);
            if (currentMember == null) {
                return MemberAuthFailed();
            }
            try {
                int result = messageService.GetMemberMessagesNum(currentMember.memberId);
                return new ResponseDto(200, Message.GET_MEMBERMESSAGES_SUCCESS, result);
            } catch (Exception ex) {
                return new ResponseDto(500, Message.GET_MEMBERMESSAGES_FAILED);
            }
        } catch (Exception ex) {
            LOGGER.error("GetMemberMessagesNum", ex.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
