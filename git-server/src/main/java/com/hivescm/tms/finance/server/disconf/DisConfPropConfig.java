package com.hivescm.tms.finance.server.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * disconf.properties disconf config
 * @author ke.huang
 * @date 2017年11月29日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "disconf.properties")
@DisconfUpdateService(classes = {DisConfPropConfig.class})
public class DisConfPropConfig implements IDisconfUpdate{

	public void reload() throws Exception {
		System.out.println("==========================================disconf properties relad========================");
	}

    
}
