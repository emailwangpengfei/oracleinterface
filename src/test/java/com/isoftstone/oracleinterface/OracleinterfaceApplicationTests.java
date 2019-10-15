package com.isoftstone.oracleinterface;

import com.isoftstone.oracleinterface.entity.InterfaceSetInfo;
import com.isoftstone.oracleinterface.exception.BaseMapperException;
import com.isoftstone.oracleinterface.exception.BusinessLogicException;
import com.isoftstone.oracleinterface.service.InterfaceSetService;
import com.isoftstone.oracleinterface.util.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OracleinterfaceApplicationTests {

    @Autowired
    InterfaceSetService interfaceSetService;

    @Test
    public void contextLoads() {
    }

    /**
     * 测试新增记录
     */
    @Test
    public void testSaveInterfaceSetInfo(){
        InterfaceSetInfo interfaceSetInfo = new InterfaceSetInfo();
        interfaceSetInfo.setLoginName("test");
        interfaceSetInfo.setPassword("test");
        interfaceSetInfo.setInterfaceUrl("test");
        interfaceSetInfo.setCurrencyId(Constant.Currency.CURRENCY_ID);
        interfaceSetInfo.setInterfaceTypeId(Constant.InterfaceType.CUXQUERY);
        try {
            interfaceSetService.saveInterfaceSet(interfaceSetInfo);
        } catch (BaseMapperException e) {
            e.printStackTrace();
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }
    }
}
