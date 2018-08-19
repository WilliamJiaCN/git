/**
 * 
 */
package com.hivescm.tms.api.dto.es.accusedgoods.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.accusedgoods.AccusedGoodsOperateTypeEnum;
import com.hivescm.tms.api.enums.bill.accusedgoods.AccusedGoodsStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author boqiang.deng
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccusedGoodsAddReq implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 公司id
	 */
	private @Mapping @ApiModelProperty(name="公司id",hidden=true) Long companyId;

	/**
	 * 状态
	 */
	@Required
	private @ApiModelProperty("状态 ACCUSED=已控货, APPROVING=审批中,RELEASED=已放货") AccusedGoodsStatusEnum status;
	/**
	 * 操作类型
	 */
	@Required
	private @ApiModelProperty("操作类型 状态字段由操作类型字段控制 务必一一对应 ACCUSED=控货, RELEASED=放货") AccusedGoodsOperateTypeEnum operationType;
	/**
	 * 运单id
	 */
	private @Required @Mapping @ApiModelProperty("运单id") Long waybillId;
	/**
	 * 申请人
	 */
	private @Mapping @ApiModelProperty(hidden = true) Integer applicant;
	/**
	 * 申请网点
	 */
	@Required
	private @Mapping @ApiModelProperty(hidden = true) Integer applicantOrg;
	/**
	 * 确认人
	 */
	private @Mapping @ApiModelProperty(hidden = true) Integer confirm;
	/**
	 * 创建人
	 */
	private @Mapping @ApiModelProperty(hidden = true) Integer createUser;
	
	private @Required @Logger @Mapping @ApiModelProperty("运单号") String code;

	private @Logger @ApiModelProperty(name = "集团ID",hidden = true) Integer groupId;

	private @Mapping @ApiModelProperty(hidden = true) String createUserName;

	private @Mapping @ApiModelProperty(name="申请人名称",hidden = true) String applicantName;

	private @Mapping @ApiModelProperty(name="确认人名称",hidden = true) String confirmName;

	private @Mapping @ApiModelProperty(name="确认网点名称",hidden = true) String confirmOrgName;

	private @Mapping @ApiModelProperty(name= "申请网点名称",hidden = true) String applicantOrgName;

}
