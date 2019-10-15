package com.isoftstone.oracleinterface.util;

/**
 * @Author wpf
 * @Date 2019/10/10 16:09
 */
public class Constant {

    private Constant(){}

    /**
     * 第一页
     */
    public static final int FIRST_PAGE = 1;
    /**
     * 每页大小
     */
    public static final int PAGE_SIZE = 5;

    public static final String BASE_MAPPER_ERROR = "BaseMapper方法异常";

    /**
     * 状态
     */
    public class Status{
        private Status(){}
        /**
         * 逻辑删除
         */
        public static final long DELETE = 0;
        /**
         * 正常保存
         */
        public static final long SAVE = 1;
    }

    /**
     * 接口类型
     */
    public class InterfaceType{
        private InterfaceType(){}

        /**
         * 数据查询接口-标号
         */
        public static final long CUXQUERY = 1L;
        /**
         * 数据查询接口-名称
         */
        public static final String CUXQUERY_NAME = "数据查询接口";
        /**
         * 通用导出接口-编号
         */
        public static final long CUXCOMMONEXP = 2L;
        /**
         * 通用导出接口-名称
         */
        public static final String CUXCOMMONEXP_NAME = "通用导出接口";
    }

    /**
     * 办事处
     */
    public class Office{
        private Office(){}

        /**
         * 办事处 id
         */
        public static final long OFFICE_ID = 1L;

        /**
         * 办事处名称
         */
        public static final String OFFICE_NAME = "粤海集团财务有限公司";
    }

    /**
     * 币种
     */
    public class Currency{
        private Currency(){}
        /**
         * 币种 id
         */
        public static final long CURRENCY_ID = 1L;

        /**
         * 币种名称
         */
        public static final String CURRENCY_NAME = "人民币";
    }

    /**
     * 操作者
     */
    public class Operator{
        private Operator(){}

        /**
         * 管理员 id
         */
        public static final long ADMIN = 9999L;
        /**
         * 管理员名字
         */
        public static final String ADMIN_NAME = "Admin";
    }
}
