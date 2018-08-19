package com.hivescm.tms.api.dto.es.handlingorder;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 装卸单明细表
 * 
 * @author LUTINGTING
 *
 */
@Data
@ToString
public class HandlingorderDetailEsDTO implements Serializable {
	private static final long serialVersionUID = -5977815314456807789L;

	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Mapping
	@ApiModelProperty("装卸单号")
	private String handlingOrderCode;
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;
	@Mapping
	@ApiModelProperty("装卸单id")
	private Long handlingorderId;

	@Mapping
	@ApiModelProperty("运单明细id")
	private Long waybillGoodsId;
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	@Mapping
	@ApiModelProperty("批次明细id（卸货时赋值）")
	private Long batchDetailId;
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode2;
	@Mapping
	@ApiModelProperty("运单号")
	private String code;
	@Mapping
	@ApiModelProperty("客户单号")
	private String customerOrderCode;

	@Mapping
	@ApiModelProperty("收货人")
	private Long dealerId;

	@Mapping
	@ApiModelProperty("收货人")
	private String dealerName;

	@Mapping
	@ApiModelProperty("收货公司")
	private String receiptCompany;
	@Mapping
	@ApiModelProperty("收货地址")
	private String receiptAddress;

	@Mapping
	@ApiModelProperty("运单明细的skuid")
	private String skuid;

	@Mapping
	@ApiModelProperty("商品条码")
	private String goodsCode;

	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;

	@Mapping
	@ApiModelProperty("包装")
	private String packingName;

	@Mapping
	@ApiModelProperty("数量")
	private Integer packageNum;

	@Mapping
	@ApiModelProperty("体积")
	private BigDecimal weight;

	@Mapping
	@ApiModelProperty("重量")
	private BigDecimal volume;

	@Mapping
	@ApiModelProperty("成本")
	private BigDecimal cost;
	@Mapping
	@ApiModelProperty("运费")
	private BigDecimal freight;
	@Mapping
	@ApiModelProperty("产值")
	private BigDecimal outputValue;
	@Mapping
	@ApiModelProperty("业务费")
	private BigDecimal busFee;
	@Mapping
	@ApiModelProperty("网点ID")
	private Integer branchId;
	@Mapping
	@ApiModelProperty("网点名称")
	private String branchName;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUserId;
	@Mapping
	@ApiModelProperty("修改人名称")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
	 /**
     * 录入人
     */
	@Mapping
    private Integer createUserId;
	@Mapping
    private String createUserName;
    /**
     * 录入时间
     */
	@Mapping
    private Long createTime;
    @Mapping
    private Integer type;
    @Mapping
    private Integer businessType;
	@Mapping
	@ApiModelProperty("装货时间")
	private Long shippingTime;
	@Mapping
	@ApiModelProperty("来源单号")
	private String batchCode;

	@Mapping
	@ApiModelProperty("来源车辆")
	private String vehicheNo;

	@Mapping
	@ApiModelProperty("装卸队")
	private String handlingTeamName;

	@Mapping
	@ApiModelProperty("负责人名称")
	private String handlingTeamLeaderName;

	@Mapping
	@ApiModelProperty("负责人电话")
	private String teamLeaderPhoneNo;

	/**
	 * 运单状态
	 */
	private @Mapping({ "waybillStatus", "status" }) @ApiModelProperty("运单状态") Integer status;

	/**
	 * 状态名称
	 */
	@Mapping
	private @ApiModelProperty("运单状态名称") String statusName;

	/**
	 * 总体积
	 */
	private @Mapping @ApiModelProperty("总体积") BigDecimal totalVolume;
	/**
	 * 总重量
	 */
	private @Mapping @ApiModelProperty("总重量") BigDecimal totalWeight;
	/**
	 * 总件数
	 */
	private @Mapping @ApiModelProperty("总件数") Integer totalNum;
	/**
	 * 目的地id
	 */
	private @Mapping @ApiModelProperty("目的地id") String destId;
	/**
	 * 目的地名称
	 */
	private @Mapping @ApiModelProperty("目的地名称") String destName;
	/**
	 * 运输线路id
	 */
	private @Mapping @ApiModelProperty("运输线路id") Long lineId;
	/**
	 * 运输线路名称
	 */
	private @Mapping @ApiModelProperty("运输线路名称	") String lineName;
	/**
	 * 回单要求（名称）
	 */
	@Mapping
	private @ApiModelProperty("回单要求") String backType;

	@Mapping
	private @ApiModelProperty("回单要求") Integer backTypeValue;

	/**
	 * 回单份数
	 */
	private @Mapping @ApiModelProperty("回单份数") Integer backNum;
	/**
	 * 回单编号
	 */
	private @Mapping @ApiModelProperty("回单编号") String backCode;

	/**
	 * 发货网点ID
	 */
	private @Mapping @ApiModelProperty("发货网点ID") Integer invoiceOrgId;
	/**
	 * 发货网点名称
	 */
	private @Mapping @ApiModelProperty("发货网点名称") String invoiceOrgName;

	/**
	 * 到达网点id
	 */
	private @Mapping @ApiModelProperty("到达网点id") Integer destOrgId;
	/**
	 * 到达网点名称
	 */
	private @Mapping @ApiModelProperty("到达网点名称	") String destOrgName;

	
}