package com.hivescm.tms.api.dto.es.regulation;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 调拨批次信息
 *
 * @author 张文龙
 */
@Data
@ToString
public class RegulationInfoEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5368674179942225992L;

	/**
     * 主键
     */
	@Mapping
	@ApiModelProperty("主键")
    private Long id;

    /**
     * 调拨批次
     */
	@Mapping
	@ApiModelProperty("调拨批次")
    private String regulationBatch;

    /**
     * 公司ID
     */
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 调出网点ID
     */
	@Mapping
	@ApiModelProperty("调出网点ID")
    private Integer departBranchId;

	/**
	 * 调出网点ID
	 */
	@Mapping
	@ApiModelProperty("调出网点名称")
	private String departBranchName;

    /**
	 * 调入网点ID
	 */
	@Mapping
	@ApiModelProperty("调入网点ID")
	private Integer arrivalBranchId;

	/**
	 * 调入网点ID
	 */
	@Mapping
	@ApiModelProperty("调入网点名称")
	private String arrivalBranchName;

    /**
     * 调入仓库ID
     */
	@Mapping
	@ApiModelProperty("调入仓库ID")
    private Integer arrivalWarehouseId;

	@Mapping
	@ApiModelProperty("调入仓库名称")
	private String arrivalWarehouseName;

    /**
     * 调入库区ID
     */
	@Mapping
	@ApiModelProperty("调入库区ID")
    private Integer arrivalWarehouseAreaId;

	@Mapping
	@ApiModelProperty("调入库区名称")
	private String arrivalWarehouseAreaName;

    /**
     * 调入库位ID
     */
	@Mapping
	@ApiModelProperty("调入库位ID")
    private Integer arrivalWarehousePositionId;

	/**
	 * 调入库位ID
	 */
	@Mapping
	@ApiModelProperty("调入库位名称")
	private String arrivalWarehousePositionName;

    /**
     * 备注信息
     */
	@Mapping
	@ApiModelProperty("备注信息")
    private String remark;

    /**
     * 状态信息
     */
	@Mapping
	@ApiModelProperty("状态信息")
    private Integer status;
	@Mapping
	@ApiModelProperty("状态信息")
	private String statusName;

    /**
     * 总件数
     */
	@Mapping
	@ApiModelProperty("总件数")
    private Integer packagenum;

    /**
     * 总票数
     */
	@Mapping
	@ApiModelProperty("总票数")
    private Integer amount;

    /**
     * 总重量
     */
	@Mapping
	@ApiModelProperty("总重量")
    private BigDecimal weight;

    /**
     * 总体积
     */
	@Mapping
	@ApiModelProperty("总体积")
    private BigDecimal volume;

    /**
     * 创建人
     */
	@Mapping
	@ApiModelProperty("创建人ID")
    private Integer createUser;

	/**
	 * 创建人
	 */
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

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人名称")
	private String updateUserName;
    /**
     * 修改时间
     */
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;

    /**
     * 出库确认人
     */
	@Mapping
	@ApiModelProperty("出库确认人ID")
    private Integer leavewarehouseConfirmUser;

	@Mapping
	@ApiModelProperty("出库确认人名称")
	private String leavewarehouseConfirmUserName;

    /**
     * 出库确认时间
     */
	@Mapping
	@ApiModelProperty("出库确认时间")
    private Long leavewarehouseConfirmTime;

    /**
     * 取消出库人
     */
	@Mapping
	@ApiModelProperty("取消出库人")
    private Integer leavewarehouseCancelUser;

	@Mapping
	@ApiModelProperty("取消出库人")
	private String leavewarehouseCancelUserName;
    /**
     * 取消出库时间
     */
	@Mapping
	@ApiModelProperty("取消出库时间")
    private Long leavewarehouseCancelTime;

    /**
     * 入库确认人
     */
	@Mapping
	@ApiModelProperty("入库确认人")
    private Integer enterwarehouseConfirmUser;
	@Mapping
	@ApiModelProperty("入库确认人")
	private String enterwarehouseConfirmUserName;

    /**
     * 入库确认时间
     */
	@Mapping
	@ApiModelProperty("入库确认时间")
    private Long enterwarehouseConfirmTime;


	public Boolean checkRegulationInfo(){
		if(this.departBranchId != null && this.departBranchName != null){
			if(this.arrivalBranchId != null && this.arrivalBranchName !=null){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

}