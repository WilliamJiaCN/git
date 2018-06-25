package com.hivescm.tms.api.dto.es.regulation;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 调拨批次运单关联信息
 *
 * @author
 */
@Data
@ToString
public class RegulationWaybillDetailEsDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1330813296176963928L;

	@Mapping
	@ApiModelProperty("主键")
    private Long id;

    /**
     * 公司ID
     */
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 调拨批次ID
     */
	@Mapping
	@ApiModelProperty("调拨批次ID")
    private Long regulationId;


    /**
     * 运单ID
     */
	@Mapping
	@ApiModelProperty("运单ID")
    private Long waybillId;

	@Mapping
	@ApiModelProperty("运单code")
	private String code;

    /**
     * 运单库存ID
     */
	@Mapping
	@ApiModelProperty("运单库存ID")
    private Long waybillStockId;

    /**
     * 状态
     */
	@Mapping
	@ApiModelProperty("状态")
    private Integer status;

	@Mapping
	@ApiModelProperty("状态名称")
	private String statusName;
    /**
     * 调拨件数
     */
	@Mapping
	@ApiModelProperty("调拨件数")
    private Integer regulationAmount;

    /**
     * 调拨重量
     */
	@Mapping
	@ApiModelProperty("调拨重量")
    private BigDecimal regulationWeight;

    /**
     * 调拨体积
     */
	@Mapping
	@ApiModelProperty("调拨体积")
    private BigDecimal regulationVolume;

    /**
     * 调拨备注信息
     */
	@Mapping
	@ApiModelProperty("调拨备注信息")
    private String remark;

    /**
     * 创建人
     */
	@Mapping
	@ApiModelProperty("创建人ID")
    private Integer createUser;

	@Mapping
	@ApiModelProperty("创建人名称")
	private String createUserName;

    /**
     * 创建时间
     */
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人
     */
	@Mapping
	@ApiModelProperty("修改人ID")
    private Integer updateUser;

	@Mapping
	@ApiModelProperty("修改人名称")
	private String updateUserName;

    /**
     * 修改时间
     */
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;

	///////////////////冗余调拨批次信息/////////////////////////////////

	@Mapping
	@ApiModelProperty("调拨批次")
	private String regulationBatch;

	@Mapping
	@ApiModelProperty("调出网点ID")
	private Integer departBranchId;

	@Mapping
	@ApiModelProperty("调出网点名称")
	private String departBranchName;

	@Mapping
	@ApiModelProperty("调入网点ID")
	private Integer arrivalBranchId;

	@Mapping
	@ApiModelProperty("调入网点名称")
	private String arrivalBranchName;

	@Mapping
	@ApiModelProperty("调入仓库ID")
	private Integer arrivalWarehouseId;

	@Mapping
	@ApiModelProperty("调入仓库名称")
	private String arrivalWarehouseName;

	@Mapping
	@ApiModelProperty("调入库区ID")
	private Integer arrivalWarehouseAreaId;

	@Mapping
	@ApiModelProperty("调入库区名称")
	private String arrivalWarehouseAreaName;

	@Mapping
	@ApiModelProperty("调入库位ID")
	private Integer arrivalWarehousePositionId;

	@Mapping
	@ApiModelProperty("调入库位名称")
	private String arrivalWarehousePositionName;

	@ApiModelProperty(value = "出库时间")
	private Long leaveWarehouseConfirmTime;

	@ApiModelProperty(value = "入库时间")
	private Long enterWarehouseConfirmTime;

	////////////////////////////冗余运单信息////////////////////////////

	@Mapping
	@ApiModelProperty("运单状态")
	private String waybillStatus;

	@Mapping
	@ApiModelProperty("付款方式")
	private String payWayName;

	@Mapping
	@ApiModelProperty("目的地")
	private String destName;

	@Mapping
	@ApiModelProperty("目的地")
	private String destination;

	@Mapping
	@ApiModelProperty("配送方式")
	private String distributionType;

	@Mapping
	@ApiModelProperty("运单备注")
	private String waybillRemark;

	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;

	@Mapping
	@ApiModelProperty("包装")
	private String packingName;

	@Mapping
	@ApiModelProperty("总运费")
	private Long totalFee;

}