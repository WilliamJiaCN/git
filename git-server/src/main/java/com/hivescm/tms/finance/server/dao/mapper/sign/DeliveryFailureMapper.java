package com.hivescm.tms.finance.server.dao.mapper.sign;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.DeliveryFailureDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

/**
 * 派送失败表
 */
@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "delivery_failure", isReadWriteSplitting = false)
public interface DeliveryFailureMapper {
	@ShardingExtensionMethod
	int deleteByPrimaryKey(@ShardingParam("companyId") Long id);
	@ShardingExtensionMethod
	int insert(@ShardingParam("companyId") DeliveryFailureDO record);
	@ShardingExtensionMethod
	int insertSelective(@ShardingParam("companyId") DeliveryFailureDO record);
	@ShardingExtensionMethod
	DeliveryFailureDO selectByPrimaryKey(@ShardingParam("companyId") Long id);
	@ShardingExtensionMethod
	int updateByPrimaryKeySelective(@ShardingParam("companyId") DeliveryFailureDO record);
	@ShardingExtensionMethod
	int updateByPrimaryKey(@ShardingParam("companyId") DeliveryFailureDO record);

}
