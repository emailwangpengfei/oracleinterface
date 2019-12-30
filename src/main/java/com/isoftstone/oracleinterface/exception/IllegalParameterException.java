package com.isoftstone.oracleinterface.exception;

import lombok.Data;

/**
 * @Author wpf
 * @Date 2019/10/12 15:31
 */
@Data
public class IllegalParameterException extends Exception {

    private String nextPage;

    public IllegalParameterException(String msg, String nextPage){
        super(msg);
        this.nextPage = nextPage;
    }
}
