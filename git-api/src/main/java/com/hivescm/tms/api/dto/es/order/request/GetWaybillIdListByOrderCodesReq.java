package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 获取要插入波次信息的运单列表DTO
 * @author 
 * @date 
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GetWaybillIdListByOrderCodesReq implements Serializable{
	private static final long serialVersionUID = -7275323801087270059L;
	@Logger
	@ApiModelProperty("外部订单编号列表")
	private List<String> orderCodes;
}
