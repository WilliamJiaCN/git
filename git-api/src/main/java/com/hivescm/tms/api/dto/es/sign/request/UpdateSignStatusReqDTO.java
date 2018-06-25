/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @date    2018年4月3日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class UpdateSignStatusReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	// 公司id
	@Required
	@Logger
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;
	
	// 运单id
	@Required
	@Logger
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	
	// 签收数量
	@Required
	@Logger
	@Mapping
	@ApiModelProperty("签收数量")
	private Integer signNumber;
	
	// 拒签数量
	@Required
	@Logger
	@Mapping
	@ApiModelProperty("拒签数量")
	private Integer refuseNumber;

	//签收状态
	@Required
	@Logger
	@Mapping
	@ApiModelProperty("签收类型")
	private Integer signType;

	//运单配送方式
	@Required
	@Mapping
	@ApiModelProperty("派送方式")
	private WaybillDistributionTypeEnum typeEnum;


	@Mapping
	private Long dispatcherDetailId;
}
