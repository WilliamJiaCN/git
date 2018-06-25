package com.hivescm.tms.finance.server.dao.mapper.sign;
import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.BackWarehouseDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class,dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "back_warehouse", isReadWriteSplitting = false)
public interface BackWarehouseMapper {
	
	@ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam @Param ("companyId") Long companyId,@Param("id") Long id);

	@ShardingExtensionMethod
    int insert(@ShardingParam("companyId") BackWarehouseDO record);

	@ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") BackWarehouseDO record);

	@ShardingExtensionMethod
    BackWarehouseDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

	@ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") BackWarehouseDO record);

	@ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") BackWarehouseDO record);
}