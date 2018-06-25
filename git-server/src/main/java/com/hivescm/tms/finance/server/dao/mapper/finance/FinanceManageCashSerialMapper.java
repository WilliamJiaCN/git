package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashBatchDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;


@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_manage_cash_serial", isReadWriteSplitting = false)
public interface FinanceManageCashSerialMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId")FinanceManageCashSerialDO record);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId")FinanceManageCashSerialDO record);

    @ShardingExtensionMethod
    int insertBatch(@ShardingParam("companyId") List<FinanceManageCashSerialDO> financeManageCashSerialDList);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId")FinanceManageCashSerialDO record);

    @ShardingExtensionMethod
    FinanceManageCashSerialDO selectByPrimaryKey(@ShardingParam @Param("companyId")Long id);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId")FinanceManageCashSerialDO record);

    @ShardingExtensionMethod
    int updateBySubmitId(@ShardingParam("companyId")FinanceManageCashSerialDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId")FinanceManageCashSerialDO record);

    @ShardingExtensionMethod
	int deleteBatch(@ShardingParam("companyId") List<FinanceManageCashSerialDO> rollCash);

    /**
     * 更新转账单号
     * @param cashUpdate
     * @return
     */
    @ShardingExtensionMethod
	int updateSubmitCodeBatch(@ShardingParam("companyId")  FinanceManageCashBatchDO cashUpdate);

    /**
     * 转账取消提交更新专用
     */
    @ShardingExtensionMethod
    int cancelCommitUpdate(@ShardingParam("companyId")  FinanceManageCashSerialDO financeManageCashSerialDO);

    /**
     * 转账删除接口:更新空值
     */
    @ShardingExtensionMethod
    int emptySpecifiedField(@ShardingParam("companyId")  FinanceManageCashSerialDO financeManageCashSerialDO);

    @ShardingExtensionMethod
    int updateBatch(@ShardingParam("companyId") List<FinanceManageCashSerialDO> financeManageCashSerialDO);

    @ShardingExtensionMethod
    int updateBatchContainsNull(@ShardingParam("companyId")List<FinanceManageCashSerialDO> financeManageCashSerialDO);
}