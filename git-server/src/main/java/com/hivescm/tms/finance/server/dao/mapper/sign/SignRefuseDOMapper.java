package com.hivescm.tms.finance.server.dao.mapper.sign;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hivescm.tms.constants.MapperConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDOExample;
import com.hivescm.tms.finance.server.dao.route.TmsFinanceRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;

@DataSourceRouting(routeRule = TmsFinanceRouteRule.class, dataSource = MapperConstants.DATASOURCE, databases =MapperConstants.DATABASES, tables = MapperConstants.TABLES, table = "sign_refuse", isReadWriteSplitting = false)
public interface SignRefuseDOMapper {

    @ShardingExtensionMethod
    int deleteByPrimaryKey(@ShardingParam @Param("companyId") Long companyId, Long id);

    @ShardingExtensionMethod
    int insert(@ShardingParam("companyId") SignRefuseDO record);

    @ShardingExtensionMethod
    int insertSelective(@ShardingParam("companyId") SignRefuseDO record);

    @ShardingExtensionMethod
    SignRefuseDO selectByPrimaryKey(@ShardingParam("companyId") Long id);

    @ShardingExtensionMethod
    SignRefuseDO selectBySignId(@ShardingParam @Param("companyId")Long companyId, @Param("signId")Long signId);

    @ShardingExtensionMethod
    int updateByExampleSelective(@ShardingParam("companyId") @Param("record") SignRefuseDO record,
                                 @Param("example") SignRefuseDOExample example);

    @ShardingExtensionMethod
    int updateByExample(@ShardingParam("companyId") SignRefuseDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKeySelective(@ShardingParam("companyId") SignRefuseDO record);

    @ShardingExtensionMethod
    int updateByPrimaryKey(@ShardingParam("companyId") SignRefuseDO record);
    
    @ShardingExtensionMethod
    int deleteBatch(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<Long> list);
    
    @ShardingExtensionMethod
    int insertBatch(@ShardingParam @Param("companyId") Long companyId,@Param("list") List<SignRefuseDO> list);
   
    @ShardingExtensionMethod
    int updateBatchRefuseSignByPrimaryKey(@ShardingParam @Param("companyId") Long companyId, @Param("record")SignRefuseDO record,@Param("list") List<Long> list);
}