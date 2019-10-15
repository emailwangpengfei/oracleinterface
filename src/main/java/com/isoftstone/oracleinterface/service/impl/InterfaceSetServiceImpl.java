package com.isoftstone.oracleinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isoftstone.oracleinterface.dto.PagerInfo;
import com.isoftstone.oracleinterface.entity.InterfaceSetInfo;
import com.isoftstone.oracleinterface.exception.BaseMapperException;
import com.isoftstone.oracleinterface.exception.BusinessLogicException;
import com.isoftstone.oracleinterface.mapper.InterfaceSetMapper;
import com.isoftstone.oracleinterface.service.InterfaceSetService;
import com.isoftstone.oracleinterface.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author wpf
 * @Date 2019/10/10 15:35
 */
@Service
public class InterfaceSetServiceImpl implements InterfaceSetService {

    @Autowired
    InterfaceSetMapper interfaceSetMapper;

    @Override
    public PagerInfo getInterfaceSetInfo(long id) throws BaseMapperException{
        InterfaceSetInfo originalInfo = null;
        try{
            originalInfo = interfaceSetMapper.selectById(id);
        }catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        PagerInfo neededInfo = null;
        if (originalInfo != null){
            neededInfo = new PagerInfo();
            neededInfo.setOfficeId(originalInfo.getOfficeId());
            neededInfo.setCurrencyId(originalInfo.getCurrencyId());
            neededInfo.setInterfaceTypeId(originalInfo.getInterfaceTypeId());
            neededInfo.setId(originalInfo.getId());
            neededInfo.setOfficeName(Constant.Office.OFFICE_NAME);
            neededInfo.setCurrencyName(Constant.Currency.CURRENCY_NAME);
            neededInfo.setInterfaceTypeName(
                    originalInfo.getInterfaceTypeId() == Constant.InterfaceType.CUXQUERY
                            ? Constant.InterfaceType.CUXQUERY_NAME
                            : Constant.InterfaceType.CUXCOMMONEXP_NAME);
            neededInfo.setInterfaceUrl(originalInfo.getInterfaceUrl());
            neededInfo.setLoginName(originalInfo.getLoginName());
            neededInfo.setPassword(originalInfo.getPassword());
        }
        return neededInfo;
    }

    @Override
    public List<PagerInfo> listAllInterfaceSetInfo(int current, int size) throws BaseMapperException, BusinessLogicException{
        List<InterfaceSetInfo> result = null;
        try {
            int pageCount = getPageCount(null);
            if (current < 1 || current > pageCount){
                throw new BusinessLogicException("当前页小于1或大于总页数");
            }
            IPage<InterfaceSetInfo> data = interfaceSetMapper.selectPage(new Page<InterfaceSetInfo>(current, size),
                    new QueryWrapper<InterfaceSetInfo>().eq("status_id", Constant.Status.SAVE));
            result = data.getRecords();
        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        return turnToPagerInfo(result);
    }

    @Override
    public List<PagerInfo> queryInterfaceSetInfo(Map map, int current, int size) throws BaseMapperException{
        List<InterfaceSetInfo> result = null;
        try {
            int pageCount = getPageCount(null);
            if (current < 1 || current > pageCount){
                throw new BusinessLogicException("当前页小于1或大于总页数");
            }
            IPage<InterfaceSetInfo> data = interfaceSetMapper.selectPage(new Page<InterfaceSetInfo>(current, size),
                    new QueryWrapper<InterfaceSetInfo>()
                            .eq("status_id", Constant.Status.SAVE)
                            .eq("currency_id", map.get("currencyId"))
                            .eq("interface_type_id", map.get("interfaceTypeId")));
            result = data.getRecords();
        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        return turnToPagerInfo(result);
    }

    @Override
    public void saveInterfaceSet(InterfaceSetInfo interfaceSetInfo) throws BaseMapperException, BusinessLogicException {
        interfaceSetInfo.setOfficeId(Constant.Office.OFFICE_ID);
        interfaceSetInfo.setStatusId(Constant.Status.SAVE);
        interfaceSetInfo.setInputUserId(Constant.Operator.ADMIN);
        interfaceSetInfo.setInputTime(new Date(System.currentTimeMillis()));
        interfaceSetInfo.setModifyTime(interfaceSetInfo.getInputTime());
        int effectedRows = 0;
        try {
            effectedRows = interfaceSetMapper.insert(interfaceSetInfo);
        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        if (effectedRows != 1){
            throw new BusinessLogicException("新增指定id时，影响行数不等于1");
        }
    }

    @Override
    public void updateInterfaceSet(InterfaceSetInfo interfaceSetInfo) throws BaseMapperException, BusinessLogicException {
        interfaceSetInfo.setModifyTime(new Date(System.currentTimeMillis()));
        int effectedRows = 0;
        try {
            effectedRows = interfaceSetMapper.updateById(interfaceSetInfo);
        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        if (effectedRows != 1){
            throw new BusinessLogicException("修改指定id时，影响行数不等于1");
        }
    }

    @Override
    public void deleteInterfaceSet(Long id) throws BaseMapperException, BusinessLogicException {
        int effectedRows = 0;
        // new 一个的原因是 防止进行逻辑删除时同时修改了别的无关列，仅限于修改如下两列
        InterfaceSetInfo temp = new InterfaceSetInfo();
        temp.setId(id);
        temp.setModifyTime(new Date(System.currentTimeMillis()));
        temp.setStatusId(Constant.Status.DELETE);
        try {
            // 逻辑删除
            effectedRows = interfaceSetMapper.updateById(temp);
        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        if (effectedRows != 1){
            throw new BusinessLogicException("删除指定id时，影响行数不等于1");
        }
    }

    @Override
    public int getPageCount(Long interfaceTypeId) throws BaseMapperException{
        int sum = 0;
        try {
            if (interfaceTypeId == null){
                sum = interfaceSetMapper.selectCount(new QueryWrapper<InterfaceSetInfo>()
                        .eq("status_id", Constant.Status.SAVE));
            } else {
                sum = interfaceSetMapper.selectCount(new QueryWrapper<InterfaceSetInfo>()
                        .eq("interface_type_id", interfaceTypeId)
                        .eq("status_id", Constant.Status.SAVE));
            }
        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        return (int) Math.ceil(sum/(double)Constant.PAGE_SIZE);
    }

    @Override
    public boolean isInterfaceUrlExist(String interfaceUrl) throws BaseMapperException {
        int row = 0;
        try {
            row = interfaceSetMapper.selectCount(new QueryWrapper<InterfaceSetInfo>()
                    .eq("status_id", Constant.Status.SAVE)
                    .eq("interface_url", interfaceUrl));

        } catch (Exception e){
            throw new BaseMapperException(Constant.BASE_MAPPER_ERROR);
        }
        return row == 1;
    }

    @Override
    public void batchDeleteInterfaceSetInfo(List<Long> ids) throws SQLException, BusinessLogicException {
        if (ids == null || ids.size() == 0){
            throw new BusinessLogicException("id集合为空");
        }
        interfaceSetMapper.batchDeleteInterfaceSetInfo(ids);
    }

    /**
     * 将从数据库查询出来的 InterfaceSetInfo集合 转变为 页面显示的 PagerInfo集合
     * @param interfaceSetInfo
     * @return
     */
    private List<PagerInfo> turnToPagerInfo(List<InterfaceSetInfo> interfaceSetInfo){
        if (interfaceSetInfo == null) {
            return null;
        }
        List<PagerInfo> pagerInfo = new ArrayList<>();
        for (InterfaceSetInfo originalInfo: interfaceSetInfo) {
            PagerInfo neededInfo = new PagerInfo();
            neededInfo.setId(originalInfo.getId());
            neededInfo.setOfficeName(Constant.Office.OFFICE_NAME);
            neededInfo.setCurrencyName(Constant.Currency.CURRENCY_NAME);
            neededInfo.setInterfaceTypeName(
                    originalInfo.getInterfaceTypeId() == Constant.InterfaceType.CUXQUERY
                            ? Constant.InterfaceType.CUXQUERY_NAME
                            : Constant.InterfaceType.CUXCOMMONEXP_NAME);
            neededInfo.setInterfaceUrl(originalInfo.getInterfaceUrl());
            neededInfo.setLoginName(originalInfo.getLoginName());
            neededInfo.setPassword(originalInfo.getPassword());
            pagerInfo.add(neededInfo);
        }
        return pagerInfo;
    }
}
