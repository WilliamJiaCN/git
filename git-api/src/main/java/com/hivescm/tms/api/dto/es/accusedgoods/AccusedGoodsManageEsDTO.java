package com.hivescm.tms.api.dto.es.accusedgoods;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 控货管理信息
 * 
 * @author zhenming.du
 * @date 2017年9月26日
 * @company 蜂网供应链
 */
@Data
@ToString
public class AccusedGoodsManageEsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private @Mapping @ApiModelProperty("主键") Long id;
	/**
	 * 公司id
	 */
	private @Mapping @ApiModelProperty("公司id") Long companyId;
	/**
	 * 控货批次
	 */
	private @Mapping @ApiModelProperty("控货批次") String batchCode;
	/**
	 * 状态
	 */
	private @Mapping @ApiModelProperty("状态") Integer status;
	/**
	 * 状态名称
	 */
	private @Mapping @ApiModelProperty("状态名称") String statusName;
	/**
	 * 操作类型
	 */
	private @Mapping @ApiModelProperty("操作类型") Integer operationType;
	/**
	 * 操作类型名称
	 */
	private @Mapping @ApiModelProperty("操作类型名称") String operationTypeName;
	/**
	 * 运单id
	 */
	private @Mapping @ApiModelProperty("运单id") Long waybillId;
	/**
	 * 申请人
	 */
	private @Mapping @ApiModelProperty("申请人") Integer applicant;
	/**
	 * 申请时间
	 */
	private @Mapping @ApiModelProperty("申请时间") Long applicantTime;
	/**
	 * 申请网点
	 */
	private @Mapping @ApiModelProperty("申请网点") Integer applicantOrg;
	/**
	 * 审核状态
	 */
	private @Mapping @ApiModelProperty("审核状态") Integer checked;
	/**
	 * 审核状态名称
	 */
	private @Mapping @ApiModelProperty("审核状态") Integer checkedName;
	/**
	 * 确认人
	 */
	private @Mapping @ApiModelProperty("确认人") Integer confirm;
	/**
	 * 确认时间
	 */
	private @Mapping @ApiModelProperty("确认时间") Long confirmTime;

	/**
	 * 确认网点
	 */
	private @Mapping @ApiModelProperty("确认网点") Integer confirmOrg;
	/**
	 * 创建人
	 */
	private @Mapping @ApiModelProperty(hidden = true) Integer createUser;
	/**
	 * 创建人名称
	 */
	private @Mapping @ApiModelProperty(hidden = true) String createUserName;
	/**
	 * 创建时间
	 */
	private @Mapping @ApiModelProperty(hidden = true) Long createTime;
	/**
	 * 修改人
	 */
	private @Mapping @ApiModelProperty(hidden = true) Integer updateUser;
	/**
	 * 修改人名称
	 */
	private @Mapping @ApiModelProperty(hidden = true) String updateUserName;
	/**
	 * 修改时间
	 */
	private @Mapping @ApiModelProperty(hidden = true) Long updateTime;

	/**
	 * 申请人名称
	 */
	private @Mapping @ApiModelProperty("申请人名称") String applicantName;

	/**
	 * 确认人名称
	 */
	private @Mapping @ApiModelProperty("确认人名称") String confirmName;

	/**
	 * 确认网点名称
	 */
	private @Mapping @ApiModelProperty("确认网点名称") String confirmOrgName;

	/**
	 * 申请网点名称
	 */
	private @Mapping @ApiModelProperty("申请网点名称") String applicantOrgName;

	// **************************************冗余运单字段*******************************************//
	/**
	 * 发货网点ID
	 */
	private @Mapping @ApiModelProperty("发货网点ID") Integer invoiceOrgId;
	/**
	 * 发货网点名称
	 */
	private @Mapping @ApiModelProperty("发货网点名称") String invoiceOrgName;

	/**
	 * 目的网点id
	 */
	private @Mapping @ApiModelProperty("到达网点id") Integer destOrgId;
	/**
	 * 目的网点名称
	 */
	private @Mapping @ApiModelProperty("到达网点名称") String destOrgName;

	private @Mapping @ApiModelProperty("运单号") String code;

	private @ApiModelProperty("收货人") String receiptUser;

	private @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;

}