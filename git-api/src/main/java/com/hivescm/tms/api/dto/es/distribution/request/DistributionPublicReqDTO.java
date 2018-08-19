package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionPublicReqDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("当前网点Id(必输)")
	private Integer currentDotId;
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;
	@Mapping
	@ApiModelProperty("每页大小(必输)")
	private Integer pageSize;
	@Mapping
	@ApiModelProperty("当前页(必输)")
	private Integer currentPage;

}

