package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinancePayment;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_payment", isReadWriteSplitting = false)
public interface FinancePaymentMapper {
    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);
    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") FinancePayment record);
    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") FinancePayment record);
    @ShardingExtensionMethod
    FinancePayment selectByPrimaryKey(@ShardingParam("companyId") Long id);
    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") FinancePayment record);
    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") FinancePayment record);

    /********************************* 自定义SQL ******************************************/
    @ShardingExtensionMethod
    int insertBatch(@ShardingParam @Param("companyId")Long companyId,@Param("list") List<FinancePayment> list);
}