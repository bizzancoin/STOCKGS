package com.xc.common.filter;


import com.xc.utils.PropertiesUtil;
import com.xc.utils.redis.CookieUtils;
import com.xc.utils.redis.RedisShardedPoolUtils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class
SessionExpireFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(SessionExpireFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String userLoginToken = CookieUtils.readLoginToken(httpServletRequest,
//                PropertiesUtil.getProperty("user.cookie.name"));
//        if (StringUtils.isNotEmpty(userLoginToken)) {
//            String userjsonstr = RedisShardedPoolUtils.get(userLoginToken);
//            if (StringUtils.isNotEmpty(userjsonstr)) {
//                RedisShardedPoolUtils.expire(userLoginToken, 5400);
//            }
//        }
//        String agentLoginToken = CookieUtils.readLoginToken(httpServletRequest,
//                PropertiesUtil.getProperty("agent.cookie.name"));
//        if (StringUtils.isNotEmpty(agentLoginToken)) {
//
//            String agentJsonStr = RedisShardedPoolUtils.get(agentLoginToken);
//            if (StringUtils.isNotEmpty(agentJsonStr)) {
//                RedisShardedPoolUtils.expire(agentLoginToken, 5400);
//            }
//        }
//        String adminLoginToken = CookieUtils.readLoginToken(httpServletRequest,
//                PropertiesUtil.getProperty("admin.cookie.name"));
//        if (StringUtils.isNotEmpty(adminLoginToken)) {
//            String adminJsonStr = RedisShardedPoolUtils.get(adminLoginToken);
//            if (StringUtils.isNotEmpty(adminJsonStr)) {
//                RedisShardedPoolUtils.expire(adminLoginToken, 5400);
//            }
//        }
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        String origin = req.getHeader("Origin");
//        if (origin == null) {
//            origin = req.getHeader("Referer");
//        }
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
        //该字段可选，是个布尔值，表示是否可以携带cookie
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Max-Age","1728000");
        if (req.getMethod().equals(HttpMethod.OPTIONS.name())){
            resp.setStatus(HttpStatus.NO_CONTENT.value());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
    }
}
