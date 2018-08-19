package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SaveChangeWaybillReqEsDTO implements Serializable {

	private static final long serialVersionUID = -7270371287389467420L;

	/**
	 * 公司Id
	 */
	@Required
	@Logger
	@ApiModelProperty("运单Id")
	private Long waybillId;
	/**
	 * 公司Id
	 */
	@Required
	@Logger
	@ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
	private Long companyId;
	
	/**
	 * 集团Id
	 */
	@Required
	@Logger
	@ApiModelProperty(value = "集团Id", notes = "前端调用时不传")
	private Integer groupId;

	/**
	 * 操作用户ID
	 */

	@ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
	private Long opUserId;

	/**
	 * 操作用户名称
	 */

	@ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
	private String opUserName;

	/**
	 * 网点ID
	 */
	@Required
	@Logger
	@ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
	private Long branchId;

	/**
	 * 网点名称
	 */
	@ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
	private String branchName;
	
	@ApiModelProperty("变更旧数据")
    private @Mapping String  oldChangeData;
    
    @ApiModelProperty("变更新数据")
    private @Mapping String  newChangeData;
    
    @ApiModelProperty("更改内容")
    private @Mapping String changeContent;
    
    @ApiModelProperty("更改备注")
    private @Mapping String changeRemark;


	@ApiModelProperty("初始创建人")
	private @Mapping Long oldCreateUser;

	@ApiModelProperty("初始创建人姓名")
	private @Mapping String oldCreateUserName;

	@ApiModelProperty("初始创建时间")
	private @Mapping Long oldCreateTime;
}
