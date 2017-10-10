package com.becheer.donation.controller;

import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.utils.HttpUtil;
import com.becheer.donation.utils.TaleUtils;
import com.becheer.donation.utils.MapCache;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13 on 2017/2/21.
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    protected MapCache cache = MapCache.single();

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

    public BaseController keywords(HttpServletRequest request, String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
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
