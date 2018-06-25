package com.hivescm.tms.finance.server.dao.mapper.sign;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.OrderPaymentInfoDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "order_payment_info", isReadWriteSplitting = false)
public interface OrderPaymentInfoDOMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") OrderPaymentInfoDO record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") OrderPaymentInfoDO record);

    @ShardingExtensionMethod
    OrderPaymentInfoDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int updateByExampleSelective(@ShardingParam("companyId") @Param("record") OrderPaymentInfoDO record);

    @ShardingExtensionMethod
    int updateByExample(@ShardingParam("companyId") @Param("record") OrderPaymentInfoDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") OrderPaymentInfoDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") OrderPaymentInfoDO record);
}