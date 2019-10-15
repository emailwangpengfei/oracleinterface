package com.isoftstone.oracleinterface.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义错误页面需要显示的错误属性
 * @Author wpf
 * @Date 2019/10/12 15:18
 */
@Component
public class CustomErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> errorInfo  = (Map<String, Object>)webRequest.getAttribute("errorInfo", 0);
        map.put("author","WPF");
        map.put("errorInfo", errorInfo);
        return map;
    }
}
