package com.isoftstone.oracleinterface.dto;

import lombok.Data;

/**
 * @Author wpf
 * @Date 2019/10/10 16:03
 */
@Data
public class PagerInfo {
    private Long id;
    private Long officeId;
    private Long currencyId;
    private Long interfaceTypeId;
    private String officeName;
    private String currencyName;
    private String interfaceTypeName;
    private String interfaceUrl;
    private String loginName;
    private String password;
}
