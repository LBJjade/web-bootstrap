package com.becheer.donation.interceptor;

/*
* MemberInterceptor
* Creator : xiaokepu
* Date : 2017-10-17
*/

import com.becheer.donation.configs.FileConfig;
import com.becheer.donation.interfaces.Access;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.becheer.donation.utils.HttpUtil.GetCurrentUser;

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
        if (access.authorities().equals("member")) {
            MemberSessionExtension currentMember=GetCurrentUser(request);
            if (currentMember==null){
                response.sendRedirect("/login");
            }else{
                return true;
            }
        }
        return false;
    }
}