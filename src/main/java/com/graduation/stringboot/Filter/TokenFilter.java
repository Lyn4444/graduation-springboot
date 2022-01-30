package com.graduation.stringboot.Filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.graduation.stringboot.entity.Result;
import com.graduation.stringboot.utils.Result.ResultUtil;
import com.graduation.stringboot.utils.Token.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TokenFilter
 *
 * @author Lyn
 * @date 2022/01/30
 */
@Component
@WebFilter(urlPatterns = "/demo/*", filterName = "tokenFilter")
public class TokenFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String LOGIN = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("tokenFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("tokenFilter doFilter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        String uri = httpServletRequest.getRequestURI();
        Result result = new Result();
        //     data中返回msg
        Map<String, Object> msg = new HashMap<>();

        if (uri.contains(LOGIN)) {
            //登陆放行
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("filter 登陆放行doFilter失败" + e.getMessage());
                msg.put("msg", "filter 登陆放行doFilter失败: " + e.getMessage());
                result = ResultUtil.error(msg);
            }
        } else {
            String reqMethod = httpServletRequest.getMethod();
            String token = ((HttpServletRequest) servletRequest).getHeader("token");
            String id = servletRequest.getParameter("id");
            String name = servletRequest.getParameter("name");
            String key = id + name;
            System.out.println(key);

            if (POST.equals(reqMethod)) {
                if (token != null) {
                    JwtUtil jwtUtil = new JwtUtil();

                    switch (jwtUtil.verify(token, key)) {
                        case 0: {
                            logger.error("用户信息验证失败");
                            msg.put("msg", "用户信息验证失败");
                            result = ResultUtil.error(msg);
                        }
                        break;
                        case -1: {
                            logger.error("token已过期");
                            msg.put("msg", "token已过期");
                            result = ResultUtil.error(msg);
                        }
                        break;
                        default: {
                            filterChain.doFilter(servletRequest, servletResponse);
                            String new_token = autoRefeshToken(token, key);
                            logger.info("用户信息验证成功");
                            msg.put("msg", "用户信息验证成功");
                            msg.put("nToken", new_token);
                            result = ResultUtil.success(msg);
                        }
                        break;
                    }
                } else {
                    logger.error("未携带token信息");
                    msg.put("msg", "未携带token信息");
                    result = ResultUtil.error(msg);
                }
            } else {
                //get请求直接放行
                try {
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (Exception e) {
                    logger.error("filter 放行doFilter失败 " + e.getMessage());
                }
            }
            PrintWriter writer = null;
            OutputStreamWriter outputStreamWriter = null;
            try {
                outputStreamWriter = new OutputStreamWriter(response.getOutputStream(),
                        StandardCharsets.UTF_8);
            } catch (IOException e) {
                logger.error("获取outputStream失败" + e.getMessage());
            }
            assert outputStreamWriter != null;
            writer = new PrintWriter(outputStreamWriter, true);
            String jsonStr = JSON.toJSONString(result);
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                logger.error("OutputStreamWriter 关闭失败 : " + e.getMessage());
            }
        }
    }

    private String autoRefeshToken(String token, String key) {
        JwtUtil jwtUtil = new JwtUtil();
        if (jwtUtil.canTokenRefreshed(token, key, new Date(System.currentTimeMillis()))) {
            return jwtUtil.refreshToken(token, key);
        }
        return token;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
