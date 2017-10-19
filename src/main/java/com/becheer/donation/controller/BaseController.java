package com.becheer.donation.controller;

import com.becheer.donation.configs.FileConfig;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.strings.Message;
import com.becheer.donation.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13 on 2017/2/21.
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    public @Autowired FileConfig fileConfig;

    /**
     * 主页的页面主题
     * @param viewName
     * @return
     */
    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public ResponseDto MemberAuthFailed(){
        return new ResponseDto(403, Message.UNAUTHORIZED);
    }

    /**
     * 获取当前登录的用户
     * @param request
     * @return
     */
    public MemberSessionExtension GetCurrentUser(HttpServletRequest request) {
        return HttpUtil.GetCurrentUser(request);
    }

    public String render_500() {
        return "comm/error_500";
    }

    public String render_404() {
        return "comm/error_404";
    }

}
