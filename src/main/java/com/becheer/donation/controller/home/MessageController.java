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

    @Access(authorities="member")
    @GetMapping(value = "")
    public String index(javax.servlet.http.HttpServletRequest request) {
        request.setAttribute("config", fileConfig);
        return this.render("home/message");
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetMessage(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        if (currentMember==null){
            return MemberAuthFailed();
        }
        if (pageSize<1||pageSize>50){
            pageSize=5;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try {
            PageInfo<MessageExtension> result=messageService.GetMessageList(currentMember.getMemberId(),pageNum,pageSize);
            return new ResponseDto(200, Message.MESSAGE_GET_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProjectType", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/num")
    @ResponseBody
    public ResponseDto GetMessagesNum(){
        try {
            int result =  messageService.GetMessagesNum();
            return new ResponseDto(200, Message.MESSAGE_NUMBER_GET_SUCCESS,result);
        }catch(Exception ex){
            return new ResponseDto(500, Message.MESSAGE_NUMBER_GET_FAILED);
        }

    }


    @PostMapping("/status")
    @ResponseBody
    public ResponseDto ChangeStatus(@RequestParam int id){
        try {
            messageService.ChangeStatus(id);
            return new ResponseDto(200, Message.CHANGE_STATUS_SUCCESS);
        }catch(Exception ex){
            return new ResponseDto(500, Message.CHANGE_STATUS_FAILED);
        }
    }

    @PostMapping("/getStatus")
    @ResponseBody
    public ResponseDto GetStatus(@RequestParam int id){
        try {
            int result = messageService.GetStatus(id);
            return new ResponseDto(200, Message.GET_STATUS_SUCCESS,result);
        }catch(Exception ex){
            return new ResponseDto(500, Message.GET_STATUS_FAILED);
        }
    }

    @PostMapping("/number")
    @ResponseBody
    public ResponseDto GetMemberMessagesNum(HttpServletRequest request){
        MemberSessionExtension currentMember=GetCurrentUser(request);
        try {
            int result = messageService.GetMemberMessagesNum(currentMember.memberId);
            return new ResponseDto(200, Message.GET_MEMBERMESSAGES_SUCCESS,result);
        }catch(Exception ex){
            return new ResponseDto(500, Message.GET_MEMBERMESSAGES_FAILED);
        }
    }
}
