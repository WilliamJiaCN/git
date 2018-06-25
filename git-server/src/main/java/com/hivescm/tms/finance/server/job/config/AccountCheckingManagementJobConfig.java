package com.hivescm.tms.finance.server.job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.hivescm.tms.finance.server.job.AccountCheckingManagementJobSchedule;

/**
 * Created by Administrator on 2017/7/10.
 */
//@Configuration
public class AccountCheckingManagementJobConfig {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private JobEventConfiguration jobEventConfiguration;
    
    private LiteJobConfiguration getLiteJobConfiguration(String cron, int shardingTotalCount, Class<?> clazz) {
    	JobCoreConfiguration coreConf = JobCoreConfiguration.newBuilder(clazz.getName(), cron,shardingTotalCount).build();
    	JobTypeConfiguration typeConf = new SimpleJobConfiguration(coreConf,clazz.getCanonicalName());
        return LiteJobConfiguration.newBuilder(typeConf).overwrite(true).build();
    }

    @Bean(initMethod = "init", name = "accountCheckingManagementJobScheduler")
    public JobScheduler accountCheckingManagementJobScheduler(
    		final AccountCheckingManagementJobSchedule job,
            @Value("${account.checking.management.cron}")  String cron,
            @Value("${account.checking.management.shardingTotalCount}")int shardingTotalCount) {
    	LiteJobConfiguration conf = getLiteJobConfiguration(cron, shardingTotalCount,AccountCheckingManagementJobConfig.class);
        return new SpringJobScheduler(job,zookeeperRegistryCenter,conf, jobEventConfiguration);
    }

}
