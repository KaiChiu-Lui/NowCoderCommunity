package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //对方法上有@LoginRequired的进行拦截 重定向到/login
        //Object handler 拦截到的东西,如果是向控制器方法发送请求,则handler是一个method
        System.out.println("LoginRequired拦截器PreHandler");
        if(handler instanceof HandlerMethod){
            //拦截到方法
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);

            if(loginRequired!=null&&hostHolder.getUser()==null){
                //需要登录 且未登录
                System.out.println("需要登录");
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }

            else System.out.println("已经登录了,可以访问对应的方法");
        }
        return true;
    }
}
