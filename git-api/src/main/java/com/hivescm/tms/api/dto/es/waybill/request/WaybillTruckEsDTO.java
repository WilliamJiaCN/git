package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;

import lombok.Data;
import lombok.ToString;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillTruckEsDTO implements Serializable {

	private static final long serialVersionUID = -6933611350149830800L;
	@Logger
	private List<Long> waybillIds;
	private	DispatcherEsDTO dispatcher;

}
