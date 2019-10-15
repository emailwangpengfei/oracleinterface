package com.isoftstone.oracleinterface.service;

import com.isoftstone.oracleinterface.dto.PagerInfo;
import com.isoftstone.oracleinterface.entity.InterfaceSetInfo;
import com.isoftstone.oracleinterface.exception.BaseMapperException;
import com.isoftstone.oracleinterface.exception.BusinessLogicException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Author wpf
 * @Date 2019/10/10 15:35
 */
public interface InterfaceSetService {

    /**
     * 查询指定id的记录
     * @param id
     * @return
     */
    PagerInfo getInterfaceSetInfo(long id) throws BaseMapperException;

    /**
     * 分页列出所有记录的信息
     * @param current 当前页
     * @param size 每页大小
     * @return
     */
    List<PagerInfo> listAllInterfaceSetInfo(int current, int size) throws BaseMapperException, BusinessLogicException;

    /**
     * 查询满足map条件的接口服务的基础信息
     * @param map 条件
     * @param current 当前页
     * @param size 每页大小
     * @return
     * @throws BaseMapperException
     */
    List<PagerInfo> queryInterfaceSetInfo(Map map, int current, int size) throws BaseMapperException;

    /**
     * 新增一条接口服务记录的基础信息的记录
     * @param interfaceSetInfo
     * @return
     * @throws BaseMapperException
     */
    void saveInterfaceSet (InterfaceSetInfo interfaceSetInfo) throws BaseMapperException, BusinessLogicException;

    /**
     * 修改一条接口服务记录的基础信息的记录
     * @param interfaceSetInfo
     * @throws BaseMapperException
     */
    void updateInterfaceSet (InterfaceSetInfo interfaceSetInfo) throws BaseMapperException, BusinessLogicException;

    /**
     * 逻辑删除一条接口服务记录的基础信息的记录
     * @param id
     * @throws BaseMapperException
     */
    void deleteInterfaceSet (Long id) throws BaseMapperException, BusinessLogicException;

    /**
     * 得到对应接口类型记录的总页数，为空则得到包含所有类型记录的总页数。都是没有删除的
     * @param interfaceTypeId 接口类型id
     * @return
     * @throws BaseMapperException
     */
    int getPageCount(Long interfaceTypeId) throws BaseMapperException;

    /**
     * 判断指定的url是否已经存在
     * @param interfaceUrl
     * @return
     */
    boolean isInterfaceUrlExist(String interfaceUrl)throws BaseMapperException;

    /**
     * 批量逻辑删除指定id的记录
     * @param ids
     * @throws SQLException
     * @throws BusinessLogicException
     */
    void batchDeleteInterfaceSetInfo(List<Long> ids) throws SQLException, BusinessLogicException;
}
