package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 寄出回单请求DTO
 * @author ke.huang
 * @date 2018年4月2日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptTransmitReqDTO implements Serializable{
	private static final long serialVersionUID = 2905384223228557741L;
	@Mapping @Required
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Mapping
	@ApiModelProperty("ID，新增不能有值")
	private Long id;
	@Mapping 
	@ApiModelProperty("寄出批次，新增不能有值")
    private String transmitBatchCode;
	@Required 
	@Mapping
	@ApiModelProperty(value="寄出时间",required=true)
	private Long transmitTime;
	@Required 
	@Mapping
	@ApiModelProperty(value="接收网点ID",required=true)
	private Long receiveOrgId;
	@Mapping
	@ApiModelProperty(value="接收网点名称",required=true)
	private String receiveOrgName;
	@Mapping 
	@ApiModelProperty("快递号/车牌号")
    private String expressVehicleNum;
	@Mapping 
	@ApiModelProperty("快递费用")
    private BigDecimal fee;
	@Mapping
	@ApiModelProperty(value="接收人姓名",required=true)
	private String receiptUserName;
	@Mapping 
	@ApiModelProperty(value="接收人",required=true)
    private Long receiptUserId;
	@Mapping 
	@ApiModelProperty("寄出备注")
    private String transmitRemark;
	@Mapping
	@ApiModelProperty(value="寄出网点ID",required=true)
	private Long transmitOrgId;
	@Mapping
	@ApiModelProperty(value="寄出网点名称",required=true)
	private String transmitOrgName;
	@Mapping @Required
	@ApiModelProperty(value="创建人",required=true,hidden=true)
	private Integer createUser;
	@Mapping @Required
	@ApiModelProperty(value="创建人名称",required=true,hidden=true) 
	private String createUserName;
	@Mapping @Required
	@ApiModelProperty(value="集团ID，用于生成批次号",required=true,hidden=true)
	private Integer groupId;
	
	@Required
	@ApiModelProperty("寄出回单")
	private List<ReceiptTransmitDetailDTO> details;
}
