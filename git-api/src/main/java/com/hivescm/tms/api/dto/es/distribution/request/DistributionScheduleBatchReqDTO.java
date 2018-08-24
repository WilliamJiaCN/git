package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.tms.api.dto.es.distribution.component.DistributionScheduleBatchRelationDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 城配智能调度创建批次请求实体
 * @author ke.huang
 * @date 2017年9月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionScheduleBatchReqDTO implements Serializable{
	private static final long serialVersionUID = 349659461456739905L;
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@ApiModelProperty(value="公司名称",required=true)
	private String companyName;
	@ApiModelProperty(value="派车网点ID",required=true)
	private Integer orgId;
	@ApiModelProperty(value="派车网点名称",required=true)
	private String orgName;
	@ApiModelProperty("操作人ID")
	private Integer createUser;
	@ApiModelProperty("操作人姓名")
	private String createUserName;
	@ApiModelProperty(value="调度关联数据",required=true)
	private List<DistributionScheduleBatchRelationDTO> relations;
}
