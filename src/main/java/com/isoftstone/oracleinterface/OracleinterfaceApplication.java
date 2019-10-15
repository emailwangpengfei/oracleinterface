package com.isoftstone.oracleinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.isoftstone.oracleinterface.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class OracleinterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleinterfaceApplication.class, args);
    }

}
