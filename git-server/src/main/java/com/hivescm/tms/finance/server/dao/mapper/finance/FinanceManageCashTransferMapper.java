package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashTransferDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_manage_cash_transfer", isReadWriteSplitting = false)
public interface FinanceManageCashTransferMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam @Param("companyId")Long companyId, @Param("id") Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") FinanceManageCashTransferDO record);


    int insertBatch(@ShardingParam("companyId") List<FinanceManageCashTransferDO> record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId")FinanceManageCashTransferDO record);

    @ShardingExtensionMethod
    FinanceManageCashTransferDO selectByPrimaryKey(@ShardingParam @Param("companyId")Long id);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId")FinanceManageCashTransferDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId")FinanceManageCashTransferDO record);

    /**
     * 转账：取消提交更新专用
     * @param record
     * @return
     */
    @ShardingExtensionMethod
    int updateCancelCommit(@ShardingParam("companyId")FinanceManageCashTransferDO record);

}
