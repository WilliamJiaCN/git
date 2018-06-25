package com.hivescm.tms.finance.server.dao.mapper.sign;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.query.SignWaybillDBQueryDTO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;
@DataSourceRouting(routeRule = TmsFinanceRouteRule.class,dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "sign", isReadWriteSplitting = false)
public interface SignMapper {
	
	@ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

	@ShardingExtensionMethod
    int insert(@ShardingParam("companyId") SignDO record);

	@ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") SignDO record);
	
	@ShardingExtensionMethod
	SignDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

	@ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") SignDO record);

	@ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") SignDO record);
	/**
	 * 根据主键批量删除
	 * @param id
	 * @return
	 */
	@ShardingExtensionMethod
    int deleteBatcheByPrimaryKey(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<Long> list);
	
	/**
	 * 批量插入
	 * @param companyId
	 * @param record
	 * @
	 */
	@ShardingExtensionMethod
    int insertBatch(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<SignDO> list);
	
	/**
	 * 批量插入
	 * @param companyId
	 * @param record
	 * @return
	 */
	@ShardingExtensionMethod
    int updateStatusBatchById(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<Long> list,@Param("status") Integer status);

	@ShardingExtensionMethod
    int updateBatchSignByPrimaryKey(@ShardingParam @Param("companyId") Long companyId, @Param("record")SignDO record,@Param("list") List<Long> list);

	@ShardingExtensionMethod
	int updateBatchSignCashierConfirmation(@ShardingParam @Param("companyId") Long companyId, @Param("record")SignDO record,@Param("list") List<Long> list);

	@ShardingExtensionMethod
	List<Long> queryDistinctWaybillId(@ShardingParam("companyId") SignWaybillDBQueryDTO queryDTO);
	@ShardingExtensionMethod
	Long countDistinctWaybillNum(@ShardingParam("companyId") SignWaybillDBQueryDTO queryDTO);
}