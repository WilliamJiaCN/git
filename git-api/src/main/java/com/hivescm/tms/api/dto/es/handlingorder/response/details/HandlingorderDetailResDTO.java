package com.hivescm.tms.api.dto.es.handlingorder.response.details;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * <p>Title: HandlingOrderDetailsDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-23-17:44
 */
@Data
@ToString
public class HandlingorderDetailResDTO {

	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;
	@Mapping
	@ApiModelProperty("装卸单id")
	private Long handlingorderId;

	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	@Mapping
	@ApiModelProperty("运单号")
	private String code;


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
	 /**
     * 录入人
     */
	@Mapping
	@ApiModelProperty("录入人")
    private Integer createUserId;
	@Mapping
	@ApiModelProperty("录入人")
    private String createUserName;
    /**
     * 录入时间
     */
	@Mapping
	@ApiModelProperty("录入时间")
    private Long createTime;

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
	
	private @Mapping @ApiModelProperty("到达网点名称	") String destOrgName;

}
