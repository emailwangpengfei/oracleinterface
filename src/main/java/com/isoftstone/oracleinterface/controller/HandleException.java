package com.isoftstone.oracleinterface.controller;

import com.isoftstone.oracleinterface.exception.BaseMapperException;
import com.isoftstone.oracleinterface.exception.BusinessLogicException;
import com.isoftstone.oracleinterface.exception.IllegalParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 * @Author wpf
 * @Date 2019/10/12 15:02
 */
@ControllerAdvice
public class HandleException {

    @ExceptionHandler({BaseMapperException.class, BusinessLogicException.class,
            SQLException.class,
            IOException.class, Exception.class})
    public String handleUnknownException(Exception e, HttpServletRequest request) {
        Map<String, Object> errorInfo = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code", 500);
        errorInfo.put("errorMsg", e.getMessage());
        request.setAttribute("errorInfo", errorInfo);
        return "forward:/error";
    }

    @ExceptionHandler({IllegalParameterException.class})
    public String handleIllegalParameterException(IllegalParameterException e, HttpServletRequest request){
        request.setAttribute("errorMsg", e.getMessage());
        return e.getNextPage();
    }
}
