package com.isoftstone.oracleinterface.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @Author wpf
 * @Date 2019/10/10 15:32
 */
@Data
@TableName("oracle_interface_set")
public class InterfaceSetInfo {
    private Long id;
    private Long officeId;
    private Long currencyId;
    private Long interfaceTypeId;
    private String interfaceUrl;
    private String loginName;
    private String password;
    private Long statusId;
    private Date inputTime;
    private Long inputUserId;
    private Date modifyTime;
}
