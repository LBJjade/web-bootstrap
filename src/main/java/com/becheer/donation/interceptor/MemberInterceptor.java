package com.becheer.donation.interceptor;

/*
* MemberInterceptor
* Creator : xiaokepu
* Date : 2017-10-17
*/

import com.alibaba.fastjson.JSONObject;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.strings.ConstString;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

// 自定义一个权限拦截器, 继承HandlerInterceptorAdapter类
public class MemberInterceptor extends HandlerInterceptorAdapter {
    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Access access = method.getAnnotation(Access.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截
            return true;
        }

        int[] authorities = access.authorities();

        //未配置权限，直接放行
        if (authorities.length == 0) {
            return true;
        }

        //未登录
        HttpSession session = request.getSession();
        if (session == null) {
            return false;
        }
        JSONObject sessionObject = (JSONObject) session.getAttribute(ConstString.LOGIN_SESSION_NAME);
        if (sessionObject == null) {
            return false;
        }
        int role = sessionObject.getIntValue("role");

        //验证权限
        for (int i = 0; i < authorities.length; i++) {
            if (authorities[i] == role) {
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}