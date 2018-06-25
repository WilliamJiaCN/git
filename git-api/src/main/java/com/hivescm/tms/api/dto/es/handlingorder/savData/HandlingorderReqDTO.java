package com.hivescm.tms.api.dto.es.handlingorder.savData;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 装卸单主表
 * 
 * @author LUTINGTING
 *
 */
@Data
@ToString
public class HandlingorderReqDTO implements Serializable {

	private static final long serialVersionUID = -5977815314456807789L;

	@Mapping
	 @Required
	 @ApiModelProperty(value = "装卸类型（1，装货 2卸货）",required=true)
	private Integer handlingType;
	@Mapping
	@ApiModelProperty(value = "修改时赋值）")
	private Long id;
	@Mapping
	@ApiModelProperty("装卸单号（修改赋值）")
	private String handlingOrderCode;
	@Mapping
	@ApiModelProperty("装卸类型名称")
	private String handlingTypeName;

	@Mapping
	@ApiModelProperty(value="来源批次类型（1派车单 2发车配载）",required=true)
	private Integer batchType;
	@Mapping
	@ApiModelProperty("来源车辆")
	private String vehicheNo;
	@Mapping
	@ApiModelProperty("来源单号")
	private String batchCode;
	@Mapping
	@ApiModelProperty(value="装卸队Id",required=true)
	private Long handlingTeam;
	
	@Mapping
	@ApiModelProperty(value="装卸队名称",required=true)
	private String handlingTeamName;

	@Mapping
	@ApiModelProperty(value="负责人",required=true)
	private Integer handlingTeamLeader;

	@Mapping
	@ApiModelProperty(value="负责人名称",required=true)
	private String handlingTeamLeaderName;

	@Mapping
	@ApiModelProperty(value="负责人电话",required=true)
	private String teamLeaderPhoneNo;


	@Mapping
	@ApiModelProperty(value="装卸时间",required=true)
	private Long handlingStartTime;


	@Mapping
	@ApiModelProperty("装卸备注")
	private String remark;


	@Mapping
	@ApiModelProperty("成本")
	private BigDecimal cost;

	/**
	 * 成本分摊方式ID(基础字典)
	 */
	@Mapping
	@ApiModelProperty("成本分摊方式ID(基础字典)")
	private Integer costTypeId;

	@Mapping
	@ApiModelProperty("成本分摊方式ID(基础字典)")
	private String costTypeName;
}