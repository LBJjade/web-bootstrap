package com.becheer.donation.controller;


import com.becheer.donation.model.extension.WebSocketMessage.RequestMessage;
import com.becheer.donation.model.extension.WebSocketMessage.ResponseMessage;
import com.becheer.donation.utils.WebSocketUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebSocketController {

    @Resource
    private WebSocketUtil webSocketUtil;

    @ResponseBody
    @PostMapping("/send")
    public void request(HttpServletRequest request, @RequestParam String message){
        String msg=message;
    }

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message){
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }

}
