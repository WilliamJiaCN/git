package com.hivescm.tms.api.dto.es.handlingorder;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

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
public class HandlingorderEsDTO implements Serializable {

	private static final long serialVersionUID = -5977815314456807789L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	@Mapping
	@ApiModelProperty("装卸单号")
	private String handlingOrderCode;
	@Mapping
	@ApiModelProperty("交接单号")
	private String warehouseReceiptNo;
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;

	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;
	@Mapping
	@ApiModelProperty("业务类型（城配or零担）")
	private Integer businessType;
	@Mapping
	@ApiModelProperty("装卸网点ID")
	private Integer branchId;
	@Mapping
	@ApiModelProperty("装卸网点名称")
	private String branchName;

	@Mapping
	@ApiModelProperty("仓库id")
	private Long warehouseId;

	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;

	@Mapping
	@ApiModelProperty("装卸类型（1，装货 2卸货）")
	private Integer handlingType;

	@Mapping
	@ApiModelProperty("装卸类型名称")
	private String handlingTypeName;

	@Mapping
	@ApiModelProperty("来源单据类型(1,交接单2，销退单3，销售单)")
	private String orderSourceType;

	@Mapping
	@ApiModelProperty("来源单号")
	private String batchCode;
	@Mapping
	@ApiModelProperty("来源批次类型（1派车单 2发车配载）")
	private Integer batchType;
	@Mapping
	@ApiModelProperty("来源车辆")
	private String vehicheNo;

	@Mapping
	@ApiModelProperty("装卸队Id")
	private Long handlingTeam;
	
	@Mapping
	@ApiModelProperty("装卸队名称")
	private String handlingTeamName;

	@Mapping
	@ApiModelProperty("负责人")
	private Integer handlingTeamLeader;

	@Mapping
	@ApiModelProperty("负责人名称")
	private String handlingTeamLeaderName;

	@Mapping
	@ApiModelProperty("负责人电话")
	private String teamLeaderPhoneNo;

	@Mapping
	@ApiModelProperty("总票数")
	private Integer totalWaybillNum;

	@Mapping
	@ApiModelProperty("装卸品种")
	private Integer totalSkuid;

	@Mapping
	@ApiModelProperty("装卸数量")
	private Integer totalGoodsNum;

	@Mapping
	@ApiModelProperty("装卸重量")
	private BigDecimal totalWeight;

	@Mapping
	@ApiModelProperty("装卸体积")
	private BigDecimal totalVolume;

	@Mapping
	@ApiModelProperty("装卸时间")
	private Long handlingStartTime;

	@Mapping
	@ApiModelProperty("装卸结束时间")
	private Long handlingEndTime;

	@Mapping
	@ApiModelProperty("装卸备注")
	private String remark;

	@Mapping
	@ApiModelProperty("主键")
	private Long orderCreateTime;

	@Mapping
	@ApiModelProperty("装卸录入人")
	private Integer orderCreateUserId;

	@Mapping
	@ApiModelProperty("装卸录入人")
	private String orderCreateUseName;

	@Mapping
	@ApiModelProperty("制单人")
	private Integer createUserId;

	@Mapping
	@ApiModelProperty("制单人名称")
	private String createUserName;

	@Mapping
	@ApiModelProperty("制单时间")
	private Long createTime;

	@Mapping
	@ApiModelProperty("状态")
	private Integer status;

	@Mapping
	@ApiModelProperty("状态")
	private String statusName;

	@Mapping
	@ApiModelProperty("成本")
	private BigDecimal cost;
	@Mapping
	@ApiModelProperty("装货时间")
	private Long shippingTime;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUserId;
	@Mapping
	@ApiModelProperty("修改人")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
	@Mapping
	@ApiModelProperty("审核人")
	private Integer checkUserId;
	@Mapping
	@ApiModelProperty("审核人名称")
	private String checkUserName;
	@Mapping
	@ApiModelProperty("审核时间")
	private Long checkTime;
	@Mapping
	@ApiModelProperty("取消审核人")
	private Integer cancelCheckUserId;
	@Mapping
	@ApiModelProperty("取消审核人名称")
	private String cancelCheckUserName;
	@Mapping
	@ApiModelProperty("取消审核时间")
	private Long cancelCheckTime;
//	@Mapping
//	@ApiModelProperty("类型区分城配、零担")
//	private Integer type;

	/**
	 * 成本分摊方式ID(基础字典)
	 */
	@Mapping
	@ApiModelProperty("成本分摊方式ID(基础字典)")
	private Integer costTypeId;

	@Mapping
	@ApiModelProperty("成本分摊方式ID(基础字典)")
	private String costTypeName;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHandlingOrderCode() {
		return handlingOrderCode;
	}

	public void setHandlingOrderCode(String handlingOrderCode) {
		this.handlingOrderCode = handlingOrderCode == null ? null : handlingOrderCode.trim();
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getHandlingType() {
		return handlingType;
	}

	public void setHandlingType(Integer handlingType) {
		this.handlingType = handlingType;
	}

	public String getOrderSourceType() {
		return orderSourceType;
	}

	public void setOrderSourceType(String orderSourceType) {
		this.orderSourceType = orderSourceType == null ? null : orderSourceType.trim();
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode == null ? null : batchCode.trim();
	}

	public String getVehicheNo() {
		return vehicheNo;
	}

	public void setVehicheNo(String vehicheNo) {
		this.vehicheNo = vehicheNo == null ? null : vehicheNo.trim();
	}


	public Integer getHandlingTeamLeader() {
		return handlingTeamLeader;
	}

	public void setHandlingTeamLeader(Integer handlingTeamLeader) {
		this.handlingTeamLeader = handlingTeamLeader;
	}

	public String getTeamLeaderPhoneNo() {
		return teamLeaderPhoneNo;
	}

	public void setTeamLeaderPhoneNo(String teamLeaderPhoneNo) {
		this.teamLeaderPhoneNo = teamLeaderPhoneNo == null ? null : teamLeaderPhoneNo.trim();
	}

	public Integer getTotalWaybillNum() {
		return totalWaybillNum;
	}

	public void setTotalWaybillNum(Integer totalWaybillNum) {
		this.totalWaybillNum = totalWaybillNum;
	}

	public Integer getTotalSkuid() {
		return totalSkuid;
	}

	public void setTotalSkuid(Integer totalSkuid) {
		this.totalSkuid = totalSkuid;
	}

	public Integer getTotalGoodsNum() {
		return totalGoodsNum;
	}

	public void setTotalGoodsNum(Integer totalGoodsNum) {
		this.totalGoodsNum = totalGoodsNum;
	}

	public BigDecimal getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}

	public BigDecimal getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(BigDecimal totalVolume) {
		this.totalVolume = totalVolume;
	}

	public Long getHandlingStartTime() {
		return handlingStartTime;
	}

	public void setHandlingStartTime(Long handlingStartTime) {
		this.handlingStartTime = handlingStartTime;
	}

	public Long getHandlingEndTime() {
		return handlingEndTime;
	}

	public void setHandlingEndTime(Long handlingEndTime) {
		this.handlingEndTime = handlingEndTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Long getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Long orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Integer getOrderCreateUserId() {
		return orderCreateUserId;
	}

	public void setOrderCreateUserId(Integer orderCreateUserId) {
		this.orderCreateUserId = orderCreateUserId;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}