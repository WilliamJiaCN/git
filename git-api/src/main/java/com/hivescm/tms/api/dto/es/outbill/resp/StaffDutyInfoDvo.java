package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>Description:</b><br>
 * 人员任职详细信息 <br><br>
 * <p>
 * <b>Note</b><br>
 * <b>ProjectName:</b> base-org-permission
 * <br><b>PackageName:</b> com.hivescm.org.dto
 * <br><b>Date:</b> 2017/10/9 20:27
 *
 * @author DongChunfu
 * @version 1.0
 * @since JDK 1.8
 */
@ApiModel(description = "人员任职信息", value = "StaffDutyInfoVo")
public class StaffDutyInfoDvo implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "集团ID")
	private Integer groupId;

	@ApiModelProperty(value = "集团名")
	private String groupName;

	@ApiModelProperty(value = " 业务单元ID")
	private Integer buId;

	@ApiModelProperty(value = " 业务单元名")
	private String buName;

	@ApiModelProperty(value = " 部门ID")
	private Integer depId;

	@ApiModelProperty(value = " 部门名")
	private String depName;

	@ApiModelProperty(value = "人员类别ID")
	private Integer staffTypeId;

	@ApiModelProperty(value = "人员类别名")
	private String staffTypeName;

	@ApiModelProperty(value = "任职开始时间")
	private Long startDate;

	@ApiModelProperty(value = "任职结束时间")
	private Long endDate;

	public StaffDutyInfoDvo() {
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getBuId() {
		return buId;
	}

	public void setBuId(Integer buId) {
		this.buId = buId;
	}

	public String getBuName() {
		return buName;
	}

	public void setBuName(String buName) {
		this.buName = buName;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Integer getStaffTypeId() {
		return staffTypeId;
	}

	public void setStaffTypeId(Integer staffTypeId) {
		this.staffTypeId = staffTypeId;
	}

	public String getStaffTypeName() {
		return staffTypeName;
	}

	public void setStaffTypeName(String staffTypeName) {
		this.staffTypeName = staffTypeName;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("StaffDutyInfoVo{");
		sb.append("groupId=").append(groupId);
		sb.append(", groupName='").append(groupName).append('\'');
		sb.append(", buId=").append(buId);
		sb.append(", buName='").append(buName).append('\'');
		sb.append(", depId=").append(depId);
		sb.append(", depName='").append(depName).append('\'');
		sb.append(", staffTypeId=").append(staffTypeId);
		sb.append(", staffTypeName='").append(staffTypeName).append('\'');
		sb.append(", startDate=").append(startDate);
		sb.append(", endDate=").append(endDate);
		sb.append('}');
		return sb.toString();
	}
}
