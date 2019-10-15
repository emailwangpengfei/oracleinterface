package com.isoftstone.oracleinterface.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isoftstone.oracleinterface.entity.InterfaceSetInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author wpf
 * @Date 2019/10/10 15:33
 */
public interface InterfaceSetMapper extends BaseMapper<InterfaceSetInfo> {

    /**
     * 根据id集合批量删除
     * @param ids
     * @return
     * @throws SQLException
     */
    boolean batchDeleteInterfaceSetInfo(@Param("ids")List<Long> ids) throws SQLException;
}
