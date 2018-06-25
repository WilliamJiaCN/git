package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单发放请求DTO
 * @author ke.huang
 * @date 2018年3月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptGrantReqDTO implements Serializable{
	private static final long serialVersionUID = 4462447491151280468L;
	@Required @Mapping
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="客户接收人")
	private String customReceiptUserName;
	@Mapping
	@ApiModelProperty(value="是否同发货公司",required=true)
	private Boolean sameInvoiceCompany;
	@Mapping @Logger
	@ApiModelProperty("快递号/车牌号")
    private String expressVehicleNum;
	@Mapping
	@ApiModelProperty("快递费用")
    private BigDecimal fee;
	@Mapping
	@ApiModelProperty("备注")
    private String remark;
	@Mapping 
	@ApiModelProperty("分摊费用")
	private BigDecimal shareFee;
	@Required
	@ApiModelProperty(value="运单ID",required=true)
	private List<Long> waybillIds;
	@Mapping @Required
	@ApiModelProperty(value="创建人名称",required=true,hidden=true) 
	private String createUserName;
	@Mapping @Required
	@ApiModelProperty(value=" 创建人",required=true,hidden=true)
	private Integer createUser;
	@Mapping @Required
	@ApiModelProperty(value="发放网点ID",required=true)
	private Long grantOrgId;
	@Mapping @Required
	@ApiModelProperty(value="发放网点名称",required=true)
	private String grantOrgName;
	@Mapping @Required
	@ApiModelProperty(value="集团ID，用于生成批次号",required=true,hidden=true)
	private Integer groupId;
	
	public Boolean isValid(){
		boolean result = true;
		if (StringUtils.isNotBlank(expressVehicleNum) || null != fee) {
			result = StringUtils.isNotBlank(expressVehicleNum) && null != fee && null != shareFee;
		}
		return result;
	}
	
}
