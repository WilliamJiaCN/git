package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>Description:</b><BR>
 * 员工及其任职信息 <br>
 *
 * @author DongChunfu
 * @version 1.0
 * Note<br> <b>ProjectName:</b> base-org-permission
 * <br><b>PackageName:</b> com.hivescm.org.dto
 * <br><b>Date:</b> 2017/9/22 14:00
 * @since JDK 1.8
 */
public class StaffWithDutyInfoDvo extends BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("所属部门ID")
	private Integer depId;

	@ApiModelProperty("所属部门名")
	private String depName;

	@ApiModelProperty("所属业务单元ID")
	private Integer buId;

	@ApiModelProperty("所属集团")
	private Integer groupId;

	@ApiModelProperty("所属业务单元名")
	private String buName;

	@ApiModelProperty("人员编码")
	private String staffCode;

	@ApiModelProperty("姓名")
	private String realname;

	@ApiModelProperty("性别(false;true女)")
	private Boolean sex;

	@ApiModelProperty("证件类型")
	private Integer certificateType;

	@ApiModelProperty("证件号码")
	private String certificateNo;

	@ApiModelProperty("生日")
	private long birthday;

	@ApiModelProperty("电话")
	private String phone;

	@ApiModelProperty("邮件")
	private String email;

	@ApiModelProperty("用工性质（0 正式工;1 临时工）")
	private Integer temporary;

	@ApiModelProperty(value = "状态(1未启用;2启用;3停用;4删除")
	private Integer status;

	@ApiModelProperty(value = "生成用户的用户id")
	private Integer userId;

	@ApiModelProperty("关联用户（true是;false否）")
	private Boolean relatedUser;

	@ApiModelProperty(value = "详细地址")
	private BaseAddressDto addressDetail;

	@ApiModelProperty("人员任职信息")
	private StaffDutyInfoDvo staffDuty;

	@ApiModelProperty("类别业务编码（供业务线调用）")
	private String bizCode;

	@ApiModelProperty("直属上级，选填")
	private Integer directSupervisor;

	public StaffWithDutyInfoDvo() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTemporary() {
		return temporary;
	}

	public void setTemporary(Integer temporary) {
		this.temporary = temporary;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getRelatedUser() {
		return relatedUser;
	}

	public void setRelatedUser(Boolean relatedUser) {
		this.relatedUser = relatedUser;
	}

	public StaffDutyInfoDvo getStaffDuty() {
		return staffDuty;
	}

	public void setStaffDuty(StaffDutyInfoDvo staffDuty) {
		this.staffDuty = staffDuty;
	}

	public BaseAddressDto getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(BaseAddressDto addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public Integer getDirectSupervisor() {
		return directSupervisor;
	}

	public void setDirectSupervisor(Integer directSupervisor) {
		this.directSupervisor = directSupervisor;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "StaffWithDutyInfoDvo{" +
				"depId=" + depId +
				", depName='" + depName + '\'' +
				", buId=" + buId +
				", groupId=" + groupId +
				", buName='" + buName + '\'' +
				", staffCode='" + staffCode + '\'' +
				", realname='" + realname + '\'' +
				", sex=" + sex +
				", certificateType=" + certificateType +
				", certificateNo='" + certificateNo + '\'' +
				", birthday=" + birthday +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", temporary=" + temporary +
				", status=" + status +
				", userId=" + userId +
				", relatedUser=" + relatedUser +
				", addressDetail=" + addressDetail +
				", staffDuty=" + staffDuty +
				", bizCode='" + bizCode + '\'' +
				", directSupervisor=" + directSupervisor +
				'}';
	}
}
