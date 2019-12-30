package com.isoftstone.oracleinterface.controller;

import com.isoftstone.oracleinterface.dto.PagerInfo;
import com.isoftstone.oracleinterface.entity.InterfaceSetInfo;
import com.isoftstone.oracleinterface.exception.BaseMapperException;
import com.isoftstone.oracleinterface.exception.BusinessLogicException;
import com.isoftstone.oracleinterface.exception.IllegalParameterException;
import com.isoftstone.oracleinterface.service.InterfaceSetService;
import com.isoftstone.oracleinterface.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wpf
 * @Date 2019/10/10 15:35
 */
@Controller
public class InterfaceSetController {

    @Autowired
    InterfaceSetService interfaceSetService;

    /**
     * 返回首页，列出第一页或者指定页的数据（每页五条记录）
     * @param request
     * @param appointedPage 指定页
     * @return
     * @throws BaseMapperException
     * @throws BusinessLogicException
     */
    @GetMapping({"/", "/index", "/index.html", "/main", "/main.html"})
    public String mainPage(HttpServletRequest request,
            @RequestParam(value = "appointedPage", required = false)Integer appointedPage)
            throws BaseMapperException, BusinessLogicException {
        List<PagerInfo> data = null;
        if (appointedPage == null) {
            data = interfaceSetService.listAllInterfaceSetInfo(Constant.FIRST_PAGE,Constant.PAGE_SIZE);
            request.setAttribute("currentPage", Constant.FIRST_PAGE);
        } else {
            data = interfaceSetService.listAllInterfaceSetInfo(appointedPage, Constant.PAGE_SIZE);
            request.setAttribute("currentPage", appointedPage);
        }
        int pageCount = interfaceSetService.getPageCount(null);
        request.setAttribute("data", data);
        request.setAttribute("currency", getCurrency());
        request.setAttribute("interfaceType", getInterfaceType());
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("withCondition", false);
        return "main";
    }

    /**
     * 条件查询，
     * @param request
     * @param currencyId 查询条件--币种类型
     * @param interfaceTypeId 查询条件--接口类型
     * @param appointedPage 查询第几页
     * @return
     * @throws BaseMapperException
     */
    @PostMapping("/query")
    public String queryInterfaceSetInfo(HttpServletRequest request,
            @RequestParam(value = "currencyId", required = false)Integer currencyId,
            @RequestParam(value = "interfaceTypeId", required = false)Integer interfaceTypeId,
            @RequestParam(value = "appointedPage", required = false)Integer appointedPage)
            throws BaseMapperException, IllegalParameterException {
        if (currencyId == null || interfaceTypeId == null){
            return "redirect:/main";
        }
        Map params = new HashMap();
        params.put("currencyId", currencyId);
        params.put("interfaceTypeId", interfaceTypeId);
        List<PagerInfo> data = null;
        if (appointedPage == null) {
            data = interfaceSetService.queryInterfaceSetInfo(params, Constant.FIRST_PAGE, Constant.PAGE_SIZE);
            request.setAttribute("currentPage", Constant.FIRST_PAGE);
        } else {
            data = interfaceSetService.queryInterfaceSetInfo(params, appointedPage, Constant.PAGE_SIZE);
            request.setAttribute("currentPage", appointedPage);
        }
        int pageCount = interfaceSetService.getPageCount(Long.parseLong(interfaceTypeId.toString()));
        request.setAttribute("data", data);
        request.setAttribute("currency", getCurrency());
        request.setAttribute("interfaceType", getInterfaceType());
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("params", params);
        request.setAttribute("withCondition", true);
        return "main";
    }

    /**
     * 转到新增页面
     * @param request
     * @return
     */
    @GetMapping("/new")
    public String toNewPage(HttpServletRequest request){
        request.setAttribute("currency", getCurrency());
        request.setAttribute("interfaceType", getInterfaceType());
        return "new";
    }

    /**
     * 新增一条记录的信息
     * @param request
     * @param interfaceSetInfo
     * @return
     * @throws BaseMapperException
     */
    @PostMapping("/save")
    public String saveInterfaceSetInfo(HttpServletRequest request, InterfaceSetInfo interfaceSetInfo)
            throws BaseMapperException, IllegalParameterException, BusinessLogicException {
        if (interfaceSetInfo == null){
            throw new IllegalParameterException("保存信息为空", null);
        }
        System.out.println(interfaceSetInfo.toString());
        interfaceSetService.saveInterfaceSet(interfaceSetInfo);
        return "redirect:/main";
    }

    /**
     * 转到修改页面，并返回对应id的相关信息
     * @param request
     * @param id
     * @return
     * @throws BaseMapperException
     */
    @GetMapping("/u{id}")
    public String toUpdatePage(HttpServletRequest request, @PathVariable("id") Long id)
            throws BaseMapperException, IllegalParameterException {
        if (id == null){
            throw new IllegalParameterException("删除指定的id为空", null);
        }
        PagerInfo data = interfaceSetService.getInterfaceSetInfo(id);
        request.setAttribute("currency", getCurrency());
        request.setAttribute("interfaceType", getInterfaceType());
        request.setAttribute("data", data);
        return "update";
    }

    /**
     * 修改一条接口类型基础服务信息
     * @param request
     * @param interfaceSetInfo
     * @return
     * @throws BaseMapperException
     */
    @PostMapping("/update")
    public String updateInterfaceSetInfo(HttpServletRequest request, InterfaceSetInfo interfaceSetInfo)
            throws BaseMapperException, IllegalParameterException, BusinessLogicException {
        if (interfaceSetInfo == null){
            throw new IllegalParameterException("修改信息为空", null);
        }
        System.out.println(interfaceSetInfo.toString());
        interfaceSetService.updateInterfaceSet(interfaceSetInfo);
        return "redirect:/main";
    }

    /**
     * 删除一条对应id的接口类型基础服务信息
     * @param request
     * @param id
     * @return
     * @throws BaseMapperException
     */
    @GetMapping("/delete{id}")
    public String deleteInterfaceSetInfo(HttpServletRequest request, @PathVariable("id") Long id)
            throws BaseMapperException, IllegalParameterException, BusinessLogicException {
        if (id == null){
            throw new IllegalParameterException("删除指定的id为空", null);
        }
        interfaceSetService.deleteInterfaceSet(id);
        return "redirect:/main";
}

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws SQLException
     * @throws BusinessLogicException
     */
    @PostMapping("/batchDelete")
    public String batchDelete(@RequestParam(value = "id", required = false) List<Long> ids) throws SQLException, BusinessLogicException {
        if (ids == null){
            return "redirect:/main";
        }
        interfaceSetService.batchDeleteInterfaceSetInfo(ids);
        return "redirect:/main";
    }

    /**
     * 异常测试
     * @throws IllegalParameterException
     */
    @GetMapping("/e")
    public void toErrorPage() throws IllegalParameterException, InterruptedException {
        Thread.sleep(2000);
        IllegalParameterException e = new IllegalParameterException("异常测试", "forward:/main");
        throw e;
    }

    @GetMapping("/isInterfaceUrlExist")
    public void isInterfaceUrlExist(@RequestParam("interfaceUrl")String interfaceUrl, HttpServletResponse response) throws BaseMapperException, IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        boolean exist = interfaceSetService.isInterfaceUrlExist(interfaceUrl);
        if (exist) {
            writer.print(0);
            return;
        }
        writer.print(1);
    }



    /**
     * 获取币种id及其名称
     * @return
     */
    private Map getCurrency(){
        Map currency = new HashMap();
        currency.put("currencyId", Constant.Currency.CURRENCY_ID);
        currency.put("currencyName", Constant.Currency.CURRENCY_NAME);
        return currency;
    }

    /**
     * 获取接口类型id及其名称
     * @return
     */
    private Map getInterfaceType(){
        Map interfaceType = new HashMap();
        interfaceType.put("cuxQueryId", Constant.InterfaceType.CUXQUERY);
        interfaceType.put("cuxQueryName", Constant.InterfaceType.CUXQUERY_NAME);
        interfaceType.put("cuxCommonExpId", Constant.InterfaceType.CUXCOMMONEXP);
        interfaceType.put("cuxCommonExpName", Constant.InterfaceType.CUXCOMMONEXP_NAME);
        return interfaceType;
    }
}
