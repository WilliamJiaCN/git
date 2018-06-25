package com.hivescm.tms.finance.server.dao.mapper.sign;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.GoodsDetailsDO;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "goods_details", isReadWriteSplitting = false)
public interface GoodsDetailsDOMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int deleteByRefuseId(@ShardingParam @Param("companyId") Long companyId, Long refuseId);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") GoodsDetailsDO record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") GoodsDetailsDO record);

    @ShardingExtensionMethod
    GoodsDetailsDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    int updateByExampleSelective(@ShardingParam("companyId") GoodsDetailsDO record);

    @ShardingExtensionMethod
    int updateByExample(@ShardingParam("companyId") GoodsDetailsDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") GoodsDetailsDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") GoodsDetailsDO record);

    @ShardingExtensionMethod
    int insertBatch(@ShardingParam @Param("companyId") Long companyId, @Param("list") List<GoodsDetailsDO> list);
}