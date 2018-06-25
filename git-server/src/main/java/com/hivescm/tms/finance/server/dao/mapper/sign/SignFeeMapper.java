package com.hivescm.tms.finance.server.dao.mapper.sign;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;
@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "sign_fee", isReadWriteSplitting = false)
public interface SignFeeMapper {
	
	@ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

	@ShardingExtensionMethod
    int insert(@ShardingParam("companyId") SignFeeDO record);
	
	@ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") SignFeeDO record);

	@ShardingExtensionMethod
    SignFeeDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

	@ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") SignFeeDO record);

	@ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") SignFeeDO record);
	
	/**
	 * 根据签收主表id批量删除
	 * @param signId
	 * @return 
	 */
	@ShardingExtensionMethod
    int deleteBatchBySignId(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<Long> list);
	
	/**
	 * 批量插入
	 * @param companyId
	 * @param record
	 * @return
	 */
	@ShardingExtensionMethod
    int insertBatch(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<SignFeeDO> list);
}