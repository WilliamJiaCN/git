package com.hivescm.tms.finance.server.dao.mapper.sign;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.BackWarehouseDetailDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;
@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "back_warehouse_detail", isReadWriteSplitting = false)
public interface BackWarehouseDetailMapper {
	
    @ShardingExtensionMethod
	int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") BackWarehouseDetailDO record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") BackWarehouseDetailDO record);

    @ShardingExtensionMethod
    BackWarehouseDetailDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") BackWarehouseDetailDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") BackWarehouseDetailDO record);
    @ShardingExtensionMethod
	int insertBatch(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<BackWarehouseDetailDO> list);
}