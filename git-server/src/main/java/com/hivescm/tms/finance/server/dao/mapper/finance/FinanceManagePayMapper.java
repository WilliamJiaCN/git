package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import com.hivescm.tms.api.dto.es.finance.request.FinanceCheckReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

/**
 * 财务管理-应付表数据访问接口
 * 
 * @author wenxiong.jia
 * @date 2018/4/27
 */
@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_manage_pay", isReadWriteSplitting = false)
public interface FinanceManagePayMapper {
	@ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") FinanceManagePayDO financeManagePayDo);

    @ShardingExtensionMethod
    int insertBatch(@ShardingParam("companyId") List<FinanceManagePayDO> financeManagePayDoList);
    /**
     * 批量修改审核状态
     * @param financeCheckReqDTO
     * @return
     */
    @ShardingExtensionMethod
    int updateCheckStatusBatch(@ShardingParam("companyId") FinanceCheckReqDTO financeCheckReqDTO);
    /**
     * 批量修改审核状态
     * @param financeCheckReqDTO
     * @return
     */
    @ShardingExtensionMethod
    int updateCancleCheckStatusBatch(@ShardingParam("companyId") FinanceCheckReqDTO financeCheckReqDTO);

    /**
     * 批量修改过账信息
     * @param financeCheckReqDTO
     * @return
     */
    @ShardingExtensionMethod
	int updatePostConfirmeBatch(@ShardingParam("companyId") FinanceCheckReqDTO financeCheckReqDTO);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") FinanceManagePayDO financeManagePayDo);

    @ShardingExtensionMethod
	int deleteByCode(@ShardingParam("companyId") FinanceDeleteReqDTO financeDeleteReqDTO);

    /**
     * 根据来源单ID和费用类型删除应付信息
     *
     * @param financeManagePayDo
     * @return
     */
    @ShardingExtensionMethod
    int deleteBySheetIdFeeType(@ShardingParam("companyId") FinanceManagePayDO financeManagePayQueryD);
}
