/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class OutWareHousePrintRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	
	@Mapping
	@ApiModelProperty("签收批次号")
	private String signBatchNumber;
	
	@Mapping
	@ApiModelProperty("收货人")
	private String signPeople;
	
	@Mapping
	@ApiModelProperty("签收人身份证号")
	private String idCard;
	
	@Mapping
	@ApiModelProperty("签收时间")
	private Long signTime;
	
	@Mapping
	@ApiModelProperty("正常签收状态名称")
	private String normalSignTypeName;

	@Mapping
	@ApiModelProperty("签收说明")
	private String signingInstructions;
	
	@Mapping
	@ApiModelProperty("签收件数")
	private Integer signNumber;
	
	@Mapping
	@ApiModelProperty("拒签件数")
	private Integer refuseNumber;
	
	@Mapping
	@ApiModelProperty("经办人名称")
	private String handlerName;

}
