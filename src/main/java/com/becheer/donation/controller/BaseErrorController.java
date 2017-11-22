package com.becheer.donation.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
*
* Creator : xiaokepu
* Date :
*/
@Controller
@RequestMapping(value = "error")
public class BaseErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "comm/error_404";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }

}