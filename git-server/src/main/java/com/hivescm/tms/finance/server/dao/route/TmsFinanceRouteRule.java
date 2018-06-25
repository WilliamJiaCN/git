package com.hivescm.tms.finance.server.dao.route;

import com.mogujie.route.rule.AbstractRouteRule;
import com.mogujie.route.rule.RouteRule;
import com.mogujie.route.rule.SimpleRouteRule;
import com.mogujie.trade.db.DataSourceRouting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 运单部分数据路由规则
 * 先预留，后期根据实际情况修改
 * 计划：
 * 1、分库、分表数量不依赖注解配置，改为系统加载
 *
 * @author 李洪春
 * @since 2017/7/24 9:31
 */
public class TmsFinanceRouteRule extends AbstractRouteRule<Long> implements RouteRule<Long> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(TmsFinanceRouteRule.class);

    private static SimpleRouteRule simpleRouteRule = new SimpleRouteRule();

    /**
     * 计算分库名称
     *
     * @param routing
     * @param shardingParam
     * @return 返回分库的完成名称
     */
    @Override
    public String calculateSchemaName(DataSourceRouting routing, Long shardingParam) {
        String schemaName = simpleRouteRule.calculateSchemaName(routing, shardingParam);
        logger.info("计算数据库名为：{}", schemaName);
        return schemaName;
    }

    /**
     * 根据分片参数值计算分表后缀
     *
     * @param routing
     * @param shardingParam
     * @return 返回分表的后缀
     */
    @Override
    public String calculateTableNameSuffix(DataSourceRouting routing, Long shardingParam) {
        return simpleRouteRule.calculateTableNameSuffix(routing, shardingParam);
    }

    /**
     * 根据分片参数值计算分表名
     *
     * @param routing
     * @param shardingPara
     * @return 返回分表的完整名称
     */
    @Override
    public String calculateTableName(DataSourceRouting routing, Long shardingPara) {
        String tableName = simpleRouteRule.calculateTableName(routing, shardingPara);
        logger.info("计算数据表名为：{}", tableName);
        return tableName;
    }

    /**
     * 根据shardingTableSuffix填充完整表后缀
     *
     * @param shardingTableSuffix
     * @return
     */
    @Override
    public String fillBit(long shardingTableSuffix) {
        return simpleRouteRule.fillBit(shardingTableSuffix);
    }

    /**
     * 根据分库策略和sharding参数获取数据源后缀
     *
     * @param routing
     * @param shardingVal
     * @return
     */
    @Override
    public int getDataSourceSuffix(DataSourceRouting routing, Long shardingVal) {
        return simpleRouteRule.getDataSourceSuffix(routing, shardingVal);
    }

    /**
     * 根据分表策略和sharding参数获取分表后缀
     *
     * @param routing
     * @param shardingVal
     * @return
     */
    @Override
    public int getTableSuffix(DataSourceRouting routing, Long shardingVal) {
        return simpleRouteRule.getTableSuffix(routing, shardingVal);
    }
}
