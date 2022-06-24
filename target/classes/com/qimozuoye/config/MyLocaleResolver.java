package com.qimozuoye.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取请求语言的参数
        String languge = request.getParameter("l");
        //如果没有传入，就返回默认的
        Locale locale = Locale.getDefault();
        //判断请求连接的国际化参数
        if (!StringUtils.isEmpty(languge)){
            //切割获取请求语言的参数以“_”为准
            String[] split = languge.split("_");
            //
            locale = new Locale(split[0],split[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
