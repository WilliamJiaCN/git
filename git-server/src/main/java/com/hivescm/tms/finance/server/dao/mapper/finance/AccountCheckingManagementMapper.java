package com.hivescm.tms.finance.server.dao.mapper.finance;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.AccountCheckingManagementDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

/**
 * 对账管理
 * @author lifan
 *
 * 2017年11月29日
 * @company 蜂网供应链管理（上海）有限公司
 */
@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "account_checking_management", isReadWriteSplitting = false)
public interface AccountCheckingManagementMapper {

	/**
	 * 按主键删除
	 * @param id
	 * @return
	 */
	@ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") @Param("id")Long id);

	/**
	 * 插入一条记录
	 * @param record
	 * @return
	 */
	@ShardingExtensionMethod
    int insert(@ShardingParam("companyId") AccountCheckingManagementDO record);

	/**
	 * 选择性插入数据
	 * @param record
	 * @return
	 */
	@ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") AccountCheckingManagementDO record);

	/**
	 * 批量插入
	 * @param companyId
	 * @param list
	 * @return
	 */
	@ShardingExtensionMethod()
	int insertBatch(@Param("list") List<AccountCheckingManagementDO> list, @ShardingParam @Param("companyId") Long companyId);
	
	/**
	 * 按主键查询
	 * @param id
	 * @return
	 */
	@ShardingExtensionMethod
    AccountCheckingManagementDO selectByPrimaryKey(@ShardingParam("companyId") @Param("id") Long id);

	/**
	 * 按主键选择性更新
	 * @param record
	 * @return
	 */
	@ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") AccountCheckingManagementDO record);

	
	/**
	 * 按主键更新
	 * @param record
	 * @return
	 */
	@ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") AccountCheckingManagementDO record);
}