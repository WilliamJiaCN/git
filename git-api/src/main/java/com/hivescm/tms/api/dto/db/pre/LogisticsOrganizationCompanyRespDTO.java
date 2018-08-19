package com.hivescm.tms.api.dto.db.pre;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年11月18日 下午1:41:49
* 物流组织公司实体
*/
@Data
@ToString
public class LogisticsOrganizationCompanyRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("物流组织id")
	private Integer companyId;
	@ApiModelProperty("物流组织名称")
	private String companyName;
}
