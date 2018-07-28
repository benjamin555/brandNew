package com.thinkgem.jeesite.modules.sys.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器，根据请求参数重写跳转的url
 */
public class ResultViewOverrideInterceptor implements HandlerInterceptor {

    public static  final String REQUEST_PRAMA_VIEW_OVERRIDE = "LH_VIEW_OVERRIDE";

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String override = httpServletRequest.getParameter(REQUEST_PRAMA_VIEW_OVERRIDE);
        if(StringUtils.isNotEmpty(override)){
            logger.info("override redirect:{}",override);
            modelAndView.setViewName("redirect:" + override);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}