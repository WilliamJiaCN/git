package com.hivescm.tms.api.dto.es.handlingorder.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 装卸单明细表
 * 
 * @author LUTINGTING
 *
 */
@Data
@ToString
public class HandlingorderDetailResDTO implements Serializable {
	

	private static final long serialVersionUID = -5977815314456807789L;

	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	
	@Mapping
	@ApiModelProperty("运单号")
	private String code;
	
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	
	@Mapping
	@ApiModelProperty("运单明细id")
	private Long waybillGoodsId;
	
	@Mapping
	@ApiModelProperty("批次明细id（卸货时赋值）")
	private Long batchDetailId;
	
	//已装件数、已装重量、已装体积、实际运费分别对应派车单明细的装货件数、
	//装货重量、装货体积、 实际运费
	@Mapping
	@ApiModelProperty("已装数量")
	private Integer packageNum;

	@Mapping
	@ApiModelProperty("已装体积")
	private BigDecimal weight;

	@Mapping
	@ApiModelProperty("已装重量")
	private BigDecimal volume;

	@Mapping
	@ApiModelProperty("实际运费")
	private BigDecimal freight;
	
	/**
	 * 类型
	 */

	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;

	@Mapping
	@ApiModelProperty("包装")
	private String packingName;

	@Mapping 
	@ApiModelProperty("总件数")
	private  Integer totalNum;
	
	@Mapping 
	@ApiModelProperty("总体积") 
	private BigDecimal totalVolume;
	
	@Mapping 
	@ApiModelProperty("总重量")
	private  BigDecimal totalWeight;
	
	@Mapping
	@ApiModelProperty("总运费")
	private BigDecimal totalFreight;
	
	@Mapping 
	@ApiModelProperty("付款方式名称") 
	private String payWayName;

	@Mapping({ "waybillStatus", "status" }) 
	@ApiModelProperty("运单状态")
	private  Integer status;
	
	@Mapping
	@ApiModelProperty("运单状态名称") 
	private String statusName;
	
	@Mapping
	@ApiModelProperty("回单要求")
	private  String backType;

	@Mapping
	@ApiModelProperty("回单要求")
	private  Integer backTypeValue;

	@Mapping 
	@ApiModelProperty("回单份数")
	private  Integer backNum;

	@Mapping 
	@ApiModelProperty("回单编号")
	private  String backCode;
	
	@Mapping 
	@ApiModelProperty("目的地id") 
	private String destId;

	@Mapping 
	@ApiModelProperty("目的地名称")
	private  String destName;
	
	@Mapping 
	@ApiModelProperty("运输线路id")
	private  Long lineId;

	@Mapping 
	@ApiModelProperty("运输线路名称	")
	private  String lineName;
	
	@Mapping 
	@ApiModelProperty("到达网点id") 
	private Integer destOrgId;
	
	@Mapping 
	@ApiModelProperty("到达网点名称	")
	private  String destOrgName;
	
	/***********************************类型******************************/
	@Mapping
	@ApiModelProperty("是否等通知")
	private Boolean iwaitNotice;

	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean iemergency;

	@Mapping
	@ApiModelProperty("是否VIP客户")
	private Boolean vip;
	
	@Mapping 
	@ApiModelProperty("是否拆单")
	private  Boolean isDismantling;
	
	@Mapping
	@ApiModelProperty("是否为异常单")
	private  Boolean exception;

}