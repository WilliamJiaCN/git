package com.hivescm.tms.api.dto.es.outbill.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <b>Description:</b><BR>
 * 信息数据基础实体，定义共有属性 <br>
 *
 * @author DongChunfu
 * @version 1.0
 * Note<br> <b>ProjectName:</b> base-org-permission
 * <br><b>PackageName:</b> com.hivescm.org.bean
 * <br><b>Date:</b> 2017/9/12 13:46
 * @since JDK 1.8
 */
@ApiModel(description = "基础新增数据实体")
public class BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("业务自增主键")
	private Integer id;

	@ApiModelProperty("创建人")
	private Integer createUser;

	@ApiModelProperty("更新人")
	private Integer updateUser;

	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	public BaseDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("BaseDto{");
		sb.append("id=").append(id);
		sb.append(", createUser=").append(createUser);
		sb.append(", updateUser=").append(updateUser);
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append('}');
		return sb.toString();
	}
}
