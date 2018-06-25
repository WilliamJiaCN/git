package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;


@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_manage_goods_grant", isReadWriteSplitting = false)
public interface FinanceManageGoodsGrantMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam @Param("companyId")Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId")FinanceManageGoodsGrantDO record);

    @ShardingExtensionMethod
    int insertBatch(@ShardingParam("companyId")List<FinanceManageGoodsGrantDO> record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId")FinanceManageGoodsGrantDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId")FinanceManageGoodsGrantDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId")FinanceManageGoodsGrantDO record);

    @ShardingExtensionMethod
    int updateBatch(@ShardingParam("companyId") List<FinanceManageGoodsGrantDO> grant);
    
    @ShardingExtensionMethod
    int delete(@ShardingParam("companyId")FinanceManageGoodsGrantDO record);

    @ShardingExtensionMethod
    int updateBatchAcountStatus(@ShardingParam("companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo);

    @ShardingExtensionMethod
    int deleteBatch(@ShardingParam("companyId")List<FinanceManageGoodsGrantDO> record);

    @ShardingExtensionMethod
    int updateBatchPayBranchId(@ShardingParam("companyId")List<FinanceManageGoodsGrantDO> financeManageGoodsGrantDOList);
}