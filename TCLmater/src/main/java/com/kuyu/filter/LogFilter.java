package com.kuyu.filter;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.kuyu.common.CommonConstants;
import com.kuyu.model.LoginUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author jt_L
 * @Date 2017/10/9
 * @Description
 */
@Component
public class LogFilter implements Filter{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        LoginUserInfo user = (LoginUserInfo) req.getSession().getAttribute(CommonConstants.LOGIN_USER_INFO);
        if (null != user) {
            if (null != user.getEmployeeModel()){
                MDC.put("userId",user.getEmployeeModel().getPerson_code());
            }else if (null != user.getWeixinUserInfo()){
                MDC.put("userId",user.getWeixinUserInfo().getOpenId());
            }
        }
        MDC.put("transId", IdWorker.get32UUID());
        try {
            filterChain.doFilter(req,resp);
        } finally {
            MDC.clear();
        }
        String url = req.getRequestURL().toString();
        if (resp.getStatus() == 404)
            log.info("发现404错误的访问,出错的网址:{}",url);

    }

    @Override
    public void destroy() {

    }
}
