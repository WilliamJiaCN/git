package com.hivescm.tms.finance.server.dao.mapper.sign;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "sign_details", isReadWriteSplitting = false)
public interface SignDetailDOMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") SignDetailDO record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") SignDetailDO record);

    @ShardingExtensionMethod
    SignDetailDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int updateByExampleSelective(@ShardingParam("companyId") @Param("record") SignDetailDO record);

    @ShardingExtensionMethod
    int updateByExample(@ShardingParam("companyId") @Param("record") SignDetailDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") SignDetailDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") SignDetailDO record);

    @ShardingExtensionMethod
    int insertBatch(@ShardingParam @Param("companyId") Long companyId, @Param("list") List<SignDetailDO> list);

    /**
     * 根据签收主表id批量删除
     *
     * @param signId
     * @return
     */
    @ShardingExtensionMethod
    int deleteBatchBySignId(@ShardingParam @Param("companyId") Long companyId, @Param("list") List<Long> list);
}