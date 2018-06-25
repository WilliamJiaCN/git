package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;


@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_manage_goods_recycle", isReadWriteSplitting = false)
public interface FinanceManageGoodsRecycleMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam @Param("companyId")Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId")FinanceManageGoodsRecycleDO record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId")FinanceManageGoodsRecycleDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId")FinanceManageGoodsRecycleDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId")FinanceManageGoodsRecycleDO record);
   
    @ShardingExtensionMethod
	int updateByBatch(@ShardingParam("companyId") List<FinanceManageGoodsRecycleDO> receipt);

    /**
     * 根据运单ID删除货款回收信息
     *
     * @param record
     * @return
     */
    @ShardingExtensionMethod
    int deleteByWaybillId(@ShardingParam("companyId") FinanceManageGoodsRecycleDO record);

    @ShardingExtensionMethod
    int batchUpdateRemitInfo(@ShardingParam("companyId")List<FinanceManageGoodsRecycleDO> financeManageGoodsRecycleDOList);
}