package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public void handlerException(Exception e, HttpServletRequest req, HttpServletResponse resp) throws Exception{
        logger.error("服务器出现异常:"+e.getMessage());
        //记录栈错误信息
        for(StackTraceElement element : e.getStackTrace()){
            logger.error(element.toString());
        }
        //获得请求的类型 根据类型返回相应方式
        String method = req.getHeader("x-requested-with");
        if("XmlHttpRequest".equals(method)){
            resp.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.write(CommunityUtil.getJSONString(1, "服务器异常!"));
        }
        else resp.sendRedirect(req.getContextPath()+"/error");
    }
}
