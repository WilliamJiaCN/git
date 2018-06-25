package com.hivescm.tms.finance.server.dao.mapper.finance;

import java.util.List;

import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceReceiptStatusUpdateDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceivableFinanceReceiptEsDTO;
import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "finance_manage_receipt", isReadWriteSplitting = false)
public interface FinanceManageReceiptMapper {

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") FinanceManageReceiptDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") FinanceManageReceiptDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") FinanceManageReceiptDO record);

    /********************************* 自定义SQL ******************************************/
    @ShardingExtensionMethod
    int insertBatch(@ShardingParam("companyId") List<FinanceManageReceiptDO> list);

    @ShardingExtensionMethod
    public int deleteByCode(@ShardingParam("companyId") FinanceDeleteReqDTO financeDeleteReqDTO);

    @ShardingExtensionMethod
    int deleteByPayway(@ShardingParam("companyId") FinanceDeleteReqDTO financeDeleteReqDTO);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelectiveBatch(@ShardingParam("companyId") List<FinanceManageReceiptDO> financeManageReceiptDOList);

    @ShardingExtensionMethod
	int updateAmount(@ShardingParam("companyId") ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);

    @ShardingExtensionMethod
	int updateSignStatus(@ShardingParam("companyId") FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO);
}