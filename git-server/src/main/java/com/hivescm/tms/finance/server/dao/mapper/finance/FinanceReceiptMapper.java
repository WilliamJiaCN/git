package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceReceipt;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class,dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_receipt", isReadWriteSplitting = false)
public interface FinanceReceiptMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") FinanceReceipt record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") FinanceReceipt record);

    @ShardingExtensionMethod
    FinanceReceipt selectByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int updateByExampleSelective(@ShardingParam("companyId") FinanceReceipt record);

    @ShardingExtensionMethod
    int updateByExample(@ShardingParam("companyId") FinanceReceipt record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") FinanceReceipt record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") FinanceReceipt record);

    @ShardingExtensionMethod
    int updateReceiptConfirm(@ShardingParam("companyId") @Param("record") FinanceReceipt record,
                             @Param("list") List<Long> list);
}