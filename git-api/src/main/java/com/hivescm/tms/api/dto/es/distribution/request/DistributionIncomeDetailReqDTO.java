package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionIncomeDetailReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	@Mapping
	@ApiModelProperty("派车单状态")
	private DispatcherStatusEnum status;
	@Mapping
	@ApiModelProperty("签收时间")
	private String signTime;
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@ApiModelProperty("当前页")
	private Integer currentPage;
}

